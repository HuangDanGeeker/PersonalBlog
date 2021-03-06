/**
 * Created by 1 on 2018/5/7.
 */
var userId;
var blogCode;
window.onload = function() {
    var location = new String(window.location);
    userId = new String(location).substr(new String(location).lastIndexOf('/')+1);
    blogCode = new String(userId).substr(new String(userId).lastIndexOf('_')+1);
    userId = new String(userId).substr(0, new String(userId).lastIndexOf('_'));
    console.log(userId);
    console.log(blogCode);
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
                $('#user-name').val(result.name);
                $('#user-email').val(result.email);
                $('#user-phone').val(result.phone);
                $('#user-QQ').val(result.qqNum);
                $('#user-intro').val(result.introduction);
                $('#user-pic').val(result.pic);
                $('#userName').text("    "+result.id + " / " +result.name)
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
                $("#blog_title").html(result.title);
                $("#blog_content").html(result.content);
                $("#blog_submitTime").html(result.submitTime);
            }else{
                layer.msg("error in query signal user info");
            }
        }
    });
};