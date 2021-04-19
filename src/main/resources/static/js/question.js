function postComment(){
    var questionId = $("#questionId").val();
    var  oneComment = $("#oneComment").val();

    console.log(oneComment.length)
    if (oneComment.length == 0 || oneComment == undefined){
        alert("请输入数据");
        return ;
    }

    $.ajax({
        type:"post",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({ "parentId":questionId,
            "content":oneComment,
            "type": "1"}),
        success:function (data){
            if (data.message == "success") {
                console.log(data);
                window.location.reload();
                $("#oneComment").val("");
            }
        }
    })

}






function postComments(e){
    var questionId = $("#questionId").val();
    var  commentId = e.getAttribute("data-id");
    var inText = $("#inlineComment-"+commentId).val();

    if (inText.length == 0 || inText == undefined){
        alert("请输入数据");
    }

    $.ajax({
        type:"post",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({ "parentId":questionId,
            "content":inText,
            "type": commentId}),
        success:function (data){
            if (data.message == "success") {
                console.log(data);
                window.location.reload();
                $("#inlineComment-"+commentId).val("");
            }
        }
    })

}




function collapseComments(e){
    var commentId = e.getAttribute("data-id");
    var comments = $("#comment-"+commentId);
    var questionId = $("#questionId").val();

    var collapse = e.getAttribute("data-collapse");
    if (collapse){
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    }else{
        $.getJSON("/comments?questionId="+questionId+"&commentId="+commentId,function (data){
            console.log(data);
        })
        comments.addClass("in");
        e.setAttribute("data-collapse","in");
        e.classList.add("active");
    }



}