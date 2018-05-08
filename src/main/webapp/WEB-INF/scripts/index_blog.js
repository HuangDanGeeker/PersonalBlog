/**
 * Created by 1 on 2018/5/7.
 */
var userId;
var blogCode;
window.onload = function() {
    var location = new String(window.location);
    blogCode = new String(location).substr(new String(location).lastIndexOf('/')+1);
    userId= new String(location).substr(0, new String(location).lastIndexOf('/'));
    userId = new String(userId).substr(new String(userId).lastIndexOf('/')+1);
    alert(userId);
    $('#indexPageLink').attr('href', "../blog/index/"+userId);
    //请求用户的基本信息
    $.ajax({
        url:"http://localhost:8080/MyBlog/queryUserInfo/"+userId,
        dataType:'jsonp',
        processData: true,
        typece:'put',
        sucss:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "success"){
                $("#profile-pic").attr("src", result.pic);
                $("#profile-name").text(result.name);
                $("#profile-qqNum").val(result.qqNum);
                $("#profile-email").val(result.email);
                $("#profile-address").val(result.address);
                $("#profile-id").text(result.id);
                $("#profile-introduction").text(result.introduction);
            }else{
                layer.msg("error in query user info");
            }
        }
    });
    //请求用户的博客信息
    $.ajax({
        url:"http://localhost:8080/MyBlog/queryBlogInfo/"+userId+"/"+blogCode,
        dataType:'jsonp',
        processData: true,
        typece:'put',
        sucss:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "success"){
                $("#profile-pic").attr("src", result.pic);

            }else{
                layer.msg("error in quer user info");
            }
        }
    });
};