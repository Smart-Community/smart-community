//ajax常用函数

document.write('<script src="/js/jquery.js" type="text/javascript" charset="utf-8"></script>');

function request(url,header,data,type,Function,skipUrl) {
    $.ajax({
        url:url,
        headers:header,
        data:data,
        type:type,
        dataType:"JSON",
        success:function (res) {
            console.log(res)
            Function(res,skipUrl);
        },
        error:function () {
            layer.msg("网络波动,请稍后再试");
        }
    })
}
//成功跳转,失败提示
function successSkip(res,url) {
    if (res.resp_code ==000000){
            location = url
    }else{
        layer.msg(res.resp_message);
    }
}
//成功失败均提示
function successHint(res) {
    layer.msg(res.resp_message);
}
//获取路径的token
function getQueryVariable() {
    var param = window.location.href.split("?")[1];
    var index = param.indexOf("=") + 1
    var query = param.substring(index);
    return (query);
}