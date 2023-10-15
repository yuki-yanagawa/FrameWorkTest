$(function(){
    $("#buttonid").on('click',function(){
        $.post("/testJson",JSON.stringify({test : "OK"}))
        .done(function(data){
            console.log(data);
        })
        .fail(function(){
		    console.log("miss");
        })
    });
})