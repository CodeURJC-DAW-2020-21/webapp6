$(document).ready(function() {
    var page=0;
    $('#bLoadMore').click(function(){
        var waitTime=1000;
        //var totalPage={{totalPage}}
        var totalPage = Mustache.render("{{totalPage}}");
        page++;
        console.log("TotalPage:"+totalPage);
        console.log("Page:"+page);
        $.ajax({
            url:"loadMore?page="+page,

            beforeSend:function(){
                $('#loading').text('Loading...');
            },

            success:function(data){
                setTimeout(function(){
                    $('#loading').text(''),
                    $('#moreProducts').append(data);
                    if(page>=totalPage){
                        document.getElementById('bLoadMore').style.display='none';
                    }
                },waitTime);
            }
        });
   });
});