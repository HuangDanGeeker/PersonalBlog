
var userId;
window.onload = function() {
    var location = new String(window.location);
    userId = new String(location).substr(new String(location).lastIndexOf('/')+1);
    $.ajax({
        url:"http://localhost:8080/MyBlog/queryUserInfo/"+"123",
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
                $('#userName').text("    "+result.id + " / " +result.name)
            }else{
                layer.msg("error in quer user info");
            }
        }
    });
};

function changeInfo(){
    var name = $('#user-name').val();
    var email = $('#user-email').val();
    var phone = $('#user-phone').val();
    var qqNum = $('#user-QQ').val();
    var introduction = $('#user-intro').val();

    $.ajax({
        url:"http://localhost:8080/MyBlog/changeUserInfo/"+userId+"/"+name+"/"+email+"/"+phone+"/"+qqNum+"/"+introduction,
        dataType:'jsonp',
        processData: true,
        typece:'put',
        sucss:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "success"){
                layer.msg("chang Info success");
            }else{
                layer.msg("chang Info failure\nunkown reason");
            }
        }
    });
}



function showCreateBlogModal(){
    $('#blogModal').modal('show');

}

function submitBlog(){
    var blogTitle = $('#blogTitle').val();
    var blogContent = $('#blogContent').val();

    $.ajax({
        url:"http://localhost:8080/MyBlog/submitBlog/"+userId+"/"+blogTitle+"/"+blogContent,
        dataType:'jsonp',
        processData: true,
        typece:'put',
        sucss:function(){
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
            var result = eval("("+XMLHttpRequest.responseText+")");
            if(result.status == "success"){
                layer.msg("submit blog success");
            }else{
                layer.msg("submit blog failure\nunkown reason");
            }
        }
    });
}
