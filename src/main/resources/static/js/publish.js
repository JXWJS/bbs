$(function () {
    $("#saveButton").click(function () {
        var title = $("#title").val();
        var description = $("#description").val();
        var tag = $("#tag").val();
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


    })

})