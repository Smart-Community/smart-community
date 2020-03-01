//ajax常用函数


function request(url,header,data,type,Function,skipUrl) {
    $.ajax({
        url:url,
        headers:header,
        data:data,
        type:type,
        dataType:"JSON",
        success:function (res) {
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