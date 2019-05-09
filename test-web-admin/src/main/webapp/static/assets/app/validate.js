/**
 * 函数对象
 * @constructor
 */
var Validate=function () {
    /**
     * 初始化 jquery valodation
     */
    var handlerInitValidate = function () {
        $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile =  /^1(3|4|5|7|8)\d{9}$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");

        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });

    };
    //将私有方法暴露出来
    return{
        init:function () {
           handlerInitValidate();
        }
    }
}();
//页面加载完成之后直接执行这个js
$(document).ready(function () {
    Validate.init();
});