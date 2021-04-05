$(function () {
    $("#saveButton").click(function () {
        var title = $("#title").val();
        var description = $("#description").val();
        var tag = $("#tag").val();
        check_publish(title,description,tag);

    })

})

function check_publish(title,description,tag){
    if (title.length==0 || description.length==0 || tag == 0) {
        $(".modal-title").html("提醒");
        document.getElementById("info").innerHTML="<h3><font color='#a52a2a'>输入的内容不能为空!</font></h3>"
        $("#publishModal").modal("show");
        return;
    }else{
        $.ajax({
            contentType:"application/json;charset=UTF-8",
            dataType:"JSON",
            type:"get",
            url:"/doPublish",
            data:{title:title,description:description,tag:tag},
            success:function (result) {
                alert(result.message);
                //清空输入框内容
                $("#title").val("");
                $("#description").val("");
                $("#tag").val("");

            },
            error:function (result) {
                alert(result.message);
            }
        });

    }

}