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
        return ;
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
    }else {

        var subComments = $("#comment-" + commentId);
        if (subComments.children().length != 2) {
            comments.addClass("in");
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {

            $.getJSON("/comments?questionId=" + questionId + "&commentId=" + commentId, function (data) {
                $.each(data.data.reverse(), function (index, result) {

                    console.log(result);
                    var commentLeftElement = $("<div/>",{
                        "class":"media-left",
                    }).append(
                        $("<img/>",{
                            "class":"media-object img-circle",
                             "style":"width:30px;height:30px",
                            "src":result.user.avatarUrl
                        }));

                    var commentMediaBody = $("<div/>",{
                        "class":"media-body"
                    }).append($("<h5/>",{
                        "class":"media-heading",
                        "html":result.user.name
                    })).append($("<div/>", {
                        "html": result.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        "html": formatTime(result.gmtCreate, 'yyyy-mm-dd')


                    })));

                    function formatTime(timestamps, format) {
                        var date = new Date(timestamps);
                        var finalstr=format;
                        finalstr=finalstr.replace('yyyy', date.getFullYear());//年
                        finalstr=finalstr.replace('mm', formatNum(date.getMonth() + 1));//月
                        finalstr=finalstr.replace('dd', formatNum(date.getDate()));//天
                        finalstr=finalstr.replace('hh',formatNum(date.getHours()));//时
                        finalstr=finalstr.replace('mi', formatNum(date.getMinutes()));//分
                        finalstr=finalstr.replace('ss', formatNum(date.getSeconds()));//秒
                        finalstr=finalstr.replace('SSS', formatMilliseconds(date.getMilliseconds()));//毫秒
                        //如果不想返回秒和毫秒，注释掉相应行数，传入参数时去掉该参数
                        return finalstr;
                    }

                    //月，天，时，分，秒不足补位,
                    // 返回的值是一个两位的数字。不过返回值不总是两位的，如果该值小于 10，则仅返回一位数字
                    //如00点，只会返回一个数字0；6:00正的分钟数返回的是一个0
                    function formatNum(arg0) {
                        let str = arg0.toString();
                        if (str.length == 1) {
                            return "0" + str;
                        } else {
                            return str;
                        }
                    }

                    //毫秒补位
                    function formatMilliseconds(arg) {
                        var str = arg.toString();
                        if (str.length == 1) {
                            return "00" + str;
                        } else if (str.length == 2) {
                            return "0" + str;
                        } else if (str.length == 3) {
                            return str;
                        }
                    }




                    var mediaElement = $("<div/>",{
                        "class":"media"
                    }).append(commentLeftElement).append(commentMediaBody);

                    var commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12"
                    }).append(mediaElement);

                    subComments.prepend(commentElement);
                })

            })
            comments.addClass("in");
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        }
    }

}