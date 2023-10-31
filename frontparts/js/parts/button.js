Button.WIDTHDEFAULT    = 10;
Button.HEIGTHDEFAULT   = 25;
Button.FONTSIZEDEFAULT = 14; 

function Button(buttonParentId, option) {
    this.buttonParentId = "#"+ buttonParentId;
    var buttonIdtmp = buttonParentId + "bt";
    this.buttonId = "#" + buttonIdtmp;
    this.buttonName = option.buttonName !== undefined ? option.buttonName : "Button";

    $(this.buttonParentId).append("<Button id='" + buttonIdtmp +"'></Button>");
    $(this.buttonId)[0].innerHTML = this.buttonName;

    if(option.func !== undefined) {
        $(this.buttonId).on('click',option.func);
    }

    var namesize = this.buttonName.length * Button.FONTSIZEDEFAULT;
    var width  = option.width !== undefined ? option.width : Button.WIDTHDEFAULT + namesize;
    var height = option.height !== undefined ? option.height : Button.HEIGTHDEFAULT;

    $(this.buttonId).width(width);
    $(this.buttonId).height(height);
    $(this.buttonId).addClass("type-button");


    return this;
}

Button.prototype.click = function() {
    $(this.buttonId).click();
}
