var lineHTML = "";
var lock = 1;
var uca = 0;
var initial = 0;
var filter = /[a-z]/


function rkey() {
    lineHTML = "";
    login.passwd.value = lineHTML;
}


function skey(i) {
    if (i == "uc") {
        if (uca == 0) {
            uca = 1;
            this.capsdisp.value = "Abc";
        }
        else {
            uca = 0;
            this.capsdisp.value = "";
        }
    }

    if (i == "caps") {
        lock = (lock * -1);
        if (lock < 0) {
            this.lockdisp.value = "ABC";
        }
        if (lock > 0) {
            this.lockdisp.value = "";
        }
    }
}

function zkey(i) {
    if ((i == ",") && (uca == 1)) {
        i = ";"
    }
    if ((i == ".") && (uca == 1)) {
        i = ":"
    }
    if ((i == "-") && (uca == 1)) {
        i = "_"
    }
    if ((i == "+") && (uca == 1)) {
        i = "*"
    }
    lineHTML += i;
    login.passwd.value = lineHTML;
    uca = 0;
    this.capsdisp.value = "";
}

function ukey(i) {
    if ((i == "ä") && ((uca == 1) || (lock < 0))) {
        i = "Ä";
    }
    if ((i == "ö") && ((uca == 1) || (lock < 0))) {
        i = "Ö";
    }
    if ((i == "ü") && ((uca == 1) || (lock < 0))) {
        i = "Ü";
    }

    lineHTML += i;
    login.passwd.value = lineHTML;
    uca = 0;
    this.capsdisp.value = "";
}

function nkey(i) {

    if ((uca == 1) && (filter.test(i))) {
        i = i.toUpperCase();
    }
    if ((lock < 0) && (filter.test(i))) {
        i = i.toUpperCase();
    }
    lineHTML += i;
    login.passwd.value = lineHTML;
    uca = 0;
    this.capsdisp.value = "";
}

function fkey(i) {
    if (i == "bs") {
        lineHTML = lineHTML.slice(0, (lineHTML.length - 1));
    }
    login.passwd.value = lineHTML;
}

function ekey() {
    opener.vollsuche.test.value = lineHTML;
    window.close();
}