var App = function () {
    //icheck
    var _masterCheckbox;
    var _checkbox;

    //用于存放id的数组
    var _idArray;

    //默认的dropzong参数
    var defaultDropzoneOpts = {
        url: "",
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
        paramName: "dropFile", // 传到后台的参数名称
    };


    /**
     * 私有化方法，初始化icheck
     */
    var handlerInitCheckbox = function () {
        // 激活 iCheck
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass: 'iradio_minimal-blue'
        });
        //获得控制端Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
        //获取全部checkbox集合
        _checkbox = $('input[type="checkbox"].minimal');
    };
    /**
     * checkbox全选功能
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            // 当前状态已选中，点击后应取消选择
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }

            // 当前状态未选中，点击后应全选
            else {
                _checkbox.iCheck("check");
            }
        });
    };


    /**
     * 初始化dataTables
     */
    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $('#dataTable').DataTable({
            "paging": true,
            "processing": true,
            "info": true,
            "lengthChange": false,
            "ordering": false,
            "searching": false,
            "serverSide": true,
            "deferRender": true,
            "ajax": {
                "url": url,
            },
            "columns": columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function (settings) {
                handlerInitCheckbox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    /**
     * 查看详情
     * @param url
     */
    var handlerShowDetail = function (url) {
        $.ajax({
            url: url,
            type: "get",
            dataType: "html",
            success: function (data) {
                $("#modal-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        })
    };


    /**
     * 树状列表
     */
    var handlerInitZtree = function (url, autoParam, callback) {
        var setting = {
            view: {
                selectedMulti: false
            },

            async: {
                enable: true,
                url: url,
                autoParam: autoParam,
            }
        };
        $.fn.zTree.init($("#myTree"), setting);

        $("#btnModalOk").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();

            //未选择
            if (nodes.length == 0) {
                alert("请选择一个节点");
            }
            //选择
            else {
                callback(nodes);
            }
        })
    };

    /**
     * 初始化dropzone
     * @param opts
     */
    var handlerInitDropzone = function (opts) {
        //关闭dropzone的自动发现功能
        Dropzone.autoDiscover = false;
        // 继承
        $.extend(defaultDropzoneOpts, opts);
        new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    };

    /**
     * 批量删除
     */
    var handlerDeleteMulti = function (url) {
        _idArray = new Array();
        //将选中元素的id放入数组中
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if (_id != null && _id != "undefine" && $(this).is(":checked")) {
                _idArray.push(_id);
            }
        });

        //判断用户是否选择了数据项
        if (_idArray.length === 0) {
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项！");
        } else {
            $("#modal-message").html("您确定删除数据项吗？");
        }

        //点击删除按钮时弹出模态框
        $("#modal-default").modal("show");

        //如果选择了模态框就进行删除操作
        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url);
        });
    };



    /**
     * 删除单笔数据
     */
    var handlerDeleteSingle = function (url, id, msg) {

        //可选参数
        if (!msg) msg = null;
        //将id放入数组中，以便和批量删除通用
        _idArray = new Array();
        _idArray.push(id);

        $("#modal-message").html(msg == null ? "您确定删除数据吗？" : msg);
        $("#modal-default").modal("show");
        //绑定删除事件
        $("#btnModalOk").bind("click", function () {
            handlerDeleteData(url);
        });
    };

    /**
     * ajax异步删除
     * @param url
     */
    var handlerDeleteData = function (url) {
        $("#modal-default").modal("hide");
        if (_idArray.length > 0) {
            setTimeout(function () {
                $.ajax({
                    "url": url,
                    "type": "POST",
                    "data": {"ids": _idArray.toString()},
                    "dataType": "JSON",
                    "success": function (data) {
                        //请求成功后，无论成功或是失败都需要弹出模态框，进行提示，解绑click事件
                        $("#btnModalOk").unbind("click");

                        //请求成功
                        if (data.status === 200) {
                            //刷新页面
                            $("#btnModalOk").bind("click", function () {
                                window.location.reload();
                            });
                        }

                        //删除失败
                        else {
                            //确定按钮的事件改为隐藏模态框
                            $("#btnModalOk").bind("click", function () {
                                $("#modal-default").modal("hide");
                            });
                        }

                        //因为无论如何都需要提示信息，所以这里的模态框是必须的
                        $("#modal-message").html(data.message);
                        $("#modal-default").modal("show");
                    }
                });
            }, 500);

        }
    };

    return {
        init: function () {


            handlerInitCheckbox();
            handlerCheckboxAll();
        },

        getCheckbox: function () {
            return _checkbox;
        },

        deleteSingle: function (url, id, msg) {
            handlerDeleteSingle(url, id, msg);
        },

        deleteMulti: function (url) {
            handlerDeleteMulti(url);
        },
        /**
         * 初始化dataTable
         * @param url
         * @param columns
         * @returns {jQuery|*}
         */
        initDataTables: function (url, columns) {
            return handlerInitDataTables(url, columns);
        },
        showDetail: function (url) {
            handlerShowDetail(url);
        },
        /**
         * 初始化dropzone
         * @param opts
         */
        initDropzone: function (opts) {
            handlerInitDropzone(opts);
        },

        /**
         * 初始化Ztree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZtree: function (url, autoParam, callback) {
            handlerInitZtree(url, autoParam, callback);


        }
    }
}();

$(document).ready(function () {
    App.init();
});