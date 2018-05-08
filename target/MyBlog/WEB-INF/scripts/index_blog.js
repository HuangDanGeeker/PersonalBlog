/**
 * Created by 1 on 2018/5/7.
 */
var userId;
window.onload = function() {

    alert("index_blog success");
    return 0;
    //请求用户的博客信息
    $.ajax({
        url:"http://localhost:8080/MyBlog/queryBlogInfo/"+"123",
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
                layer.msg("error in quer user info");
            }
        }
    });
};
