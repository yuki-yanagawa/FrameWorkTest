var MainView = {
};

$(function(){
    // $("#buttonid").on('click',function(){
    //     $.post("/testJson",JSON.stringify({test : "OK"}))
    //     .done(function(data){
    //         console.log(data);
    //     })
    //     .fail(function(){
	// 	    console.log("miss");
    //     })
    // });
    option = {
        buttonName : 'performanceCheck',
        func : function() {
            alert("TEST");
        },
    };
    MainView.partsButton = new Button("buttonId",option);
    // optiontab = {

    // };
    // MainView.partsTabAll = new Tab("allPage","tabbtn","tabarea", optiontab);
    
})