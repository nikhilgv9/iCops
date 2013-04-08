var command = "";
var cpos;


function InitToolbarButtons() {
    var kids = document.getElementsByTagName('DIV');

    for (var i = 0; i < kids.length; i++) {
        if (kids[i].className == "imagebutton") {
            kids[i].onmouseover = tbmouseover;
            kids[i].onmouseout = tbmouseout;
            kids[i].onmousedown = tbmousedown;
            kids[i].onmouseup = tbmouseup;
            kids[i].onclick = tbclick;
        }
    }
}

function tbmousedown(e) {
    var evt = e ? e : window.event;

    this.firstChild.style.left = 2;
    this.firstChild.style.top = 2;
    this.style.border = "inset 2px";
    if (evt.returnValue) {
        evt.returnValue = false;
    } else if (evt.preventDefault) {
        evt.preventDefault();
    } else {
        return false;
    }
    return false;
}

function tbmouseup() {
    this.firstChild.style.left = 1;
    this.firstChild.style.top = 1;
    this.style.border = "outset 2px";
}

function tbmouseout() {
    this.style.border = "solid 2px #C0C0C0";
}

function tbmouseover() {
    this.style.border = "outset 2px";
}

function insertNodeAtSelection(win, insertNode) {
    var sel = win.getSelection();
    var range = sel.getRangeAt(0);

    sel.removeAllRanges();

    range.deleteContents();

    // get location of current selection
    var container = range.startContainer;
    var pos = range.startOffset;

    // make a new range for the new selection
    range = document.createRange();

    if (container.nodeType == 3 && insertNode.nodeType == 3) {

        // if we insert text in a textnode, do optimized insertion
        container.insertData(pos, insertNode.nodeValue);

        // put cursor after inserted text
        range.setEnd(container, pos + insertNode.length);
        range.setStart(container, pos + insertNode.length);

    } else {


        var afterNode;
        if (container.nodeType == 3) {

            var textNode = container;
            container = textNode.parentNode;
            var text = textNode.nodeValue;

            // text before the split
            var textBefore = text.substr(0, pos);
            // text after the split
            var textAfter = text.substr(pos);

            var beforeNode = document.createTextNode(textBefore);
            afterNode = document.createTextNode(textAfter);

            // insert the 3 new nodes before the old one
            container.insertBefore(afterNode, textNode);
            container.insertBefore(insertNode, afterNode);
            container.insertBefore(beforeNode, insertNode);

            // remove the old node
            container.removeChild(textNode);

        } else {

            // else simply insert the node
            afterNode = container.childNodes[pos];
            container.insertBefore(insertNode, afterNode);
        }

        range.setEnd(afterNode, 0);
        range.setStart(afterNode, 0);
    }

    sel.addRange(range);
}

function getOffsetTop(elm) {

    var mOffsetTop = elm.offsetTop;
    var mOffsetParent = elm.offsetParent;

    while (mOffsetParent) {
        mOffsetTop += mOffsetParent.offsetTop;
        mOffsetParent = mOffsetParent.offsetParent;
    }

    return mOffsetTop;
}

function getOffsetLeft(elm) {

    var mOffsetLeft = elm.offsetLeft;
    var mOffsetParent = elm.offsetParent;

    while (mOffsetParent) {
        mOffsetLeft += mOffsetParent.offsetLeft;
        mOffsetParent = mOffsetParent.offsetParent;
    }

    return mOffsetLeft;
}

function changecolor(color)
{
    document.getElementById("edit").contentWindow.focus();
    restoreSelection(cpos);
    //document.getElementById("edit").contentWindow.document.execCommand(command, false,color);
    try{
        document.getElementById("edit").contentWindow.document.execCommand(command, false,color);
    }
    catch(e)
    {
        document.getElementById("edit").contentWindow.document.execCommand("BackColor", false,color);;
    }
}

function tbclick() {
    cpos=saveSelection();
    if ((this.id == "forecolor") || (this.id == "hilitecolor")) {
        command = this.id;
        buttonElement = document.getElementById(this.id);
        document.getElementById("colorpalette").style.left = getOffsetLeft(buttonElement);
        document.getElementById("colorpalette").style.top = getOffsetTop(buttonElement) + buttonElement.offsetHeight;
        document.getElementById("colorpalette").style.visibility = "visible";
    } else if (this.id == "createlink") {
        var szURL = prompt("Enter a URL:", "http://");
        if ((szURL != null) && (szURL != "")) {
            document.getElementById('edit').contentWindow.document.execCommand("CreateLink", false, szURL);
        }
    } else if (this.id == "createimage") {
        showupdiv();
        /*imagePath = prompt('Enter Image URL:', 'http://');
        if ((imagePath != null) && (imagePath != "")) {
            document.getElementById('edit').contentWindow.focus();
            document.getElementById('edit').contentWindow.document.execCommand("InsertImage", false, imagePath);
        }*/
    } else if (this.id == "createtable") {
        e = document.getElementById("edit");
        rowstext = prompt("enter rows");
        colstext = prompt("enter cols");
        rows = parseInt(rowstext);
        cols = parseInt(colstext);
        if ((rows > 0) && (cols > 0)) {
            document.getElementById('edit').contentWindow.focus();
            restoreSelection(cpos);
            table = e.contentWindow.document.createElement("table");
            table.setAttribute("border", "1");
            table.setAttribute("cellpadding", "2");
            table.setAttribute("cellspacing", "2");
            tbody = e.contentWindow.document.createElement("tbody");
            for (var i = 0; i < rows; i++) {
                tr = e.contentWindow.document.createElement("tr");
                for (var j = 0; j < cols; j++) {
                    td = e.contentWindow.document.createElement("td");
                    br = e.contentWindow.document.createElement("br");
                    td.appendChild(br);
                    tr.appendChild(td);
                }
                tbody.appendChild(tr);
            }
            table.appendChild(tbody);
            insertNodeAtSelection(e.contentWindow, table);
        }
    } else {
        document.getElementById('edit').contentWindow.document.execCommand(this.id, false, null);
    }
}

function Select(selectname) {
    var cursel = document.getElementById(selectname).selectedIndex;
    /* First one is always a label */
    if (cursel != 0) {
        var selected = document.getElementById(selectname).options[cursel].value;
        document.getElementById('edit').contentWindow.document.execCommand(selectname, false, selected);
        document.getElementById(selectname).selectedIndex = 0;
    }
    document.getElementById("edit").contentWindow.focus();
}

function dismisscolorpalette() {
    document.getElementById("colorpalette").style.visibility = "hidden";
}

function Start() {  
    document.getElementById('edit').contentWindow.document.designMode = "on";
    try {
        document.getElementById('edit').contentWindow.document.execCommand("undo", false, null);
    } catch (e) {
        alert("not supported in browser");
    }
    InitToolbarButtons();
    if (document.addEventListener) {
        document.addEventListener("mousedown", dismisscolorpalette, true);
        document.getElementById("edit").contentWindow.document.addEventListener("mousedown", dismisscolorpalette, true);
        document.addEventListener("keypress", dismisscolorpalette, true);
        document.getElementById("edit").contentWindow.document.addEventListener("keypress", dismisscolorpalette, true);
    } else if (document.attachEvent) {
        document.attachEvent("onmousedown", dismisscolorpalette, true);
        document.getElementById("edit").contentWindow.document.attachEvent("onmousedown", dismisscolorpalette, true);
        document.attachEvent("onkeypress", dismisscolorpalette, true);
        document.getElementById("edit").contentWindow.document.attachEvent("onkeypress", dismisscolorpalette, true);
    }
}

function viewsource(source) {
    var html;
    if (source) {
        html = document.getElementById('edit').contentWindow.document.body.innerHTML;
        document.getElementById('edit').contentWindow.document.body.innerText = html;
        document.getElementById("toolbar1").style.visibility = "hidden";
        document.getElementById("toolbar2").style.visibility = "hidden";
    } else {
        html = document.getElementById('edit').contentWindow.document.body.innerText;
        document.getElementById('edit').contentWindow.document.body.innerHTML = html;
        document.getElementById("toolbar1").style.visibility = "visible";
        document.getElementById("toolbar2").style.visibility = "visible";
    }
}

function usecss(source) {
    document.getElementById('edit').contentWindow.document.execCommand("useCSS", false, !(source));
}

function readonly(source) {
    document.getElementById('edit').contentWindow.document.execCommand("readonly", false, !(source));
}

    function showupdiv(){
        document.getElementById('fileupload').style.visibility = 'visible';
    }

    function startUpload() {
        document.getElementById('f1_upload_process').style.visibility = 'visible';return true;
    }

    function stopUpload(success,imagePath)
    {
        var result = '';
        if (success == 1) {

            document.getElementById('f1_upload_process').style.visibility = 'hidden';
            document.getElementById('fileupload').style.visibility = 'hidden';
           if ((imagePath != null) && (imagePath != "")) {
               document.getElementById('edit').contentWindow.focus();
               restoreSelection(cpos);
               document.getElementById('edit').contentWindow.document.execCommand("InsertImage", false, imagePath);
            }
        }
   }




function getContentWindow() {
 return document.getElementById("edit").contentWindow;
}

function saveSelection() {
 var win = getContentWindow();
 var doc = win.document;
 var sel = win.getSelection ? win.getSelection() : doc.selection;
 var range;

 if (sel) {
  if (sel.createRange) {
   range = sel.createRange();
  } /* else if (sel.getRangeAt) {
   range = sel.getRangeAt(0);
  } else if (sel.anchorNode && sel.focusNode && doc.createRange) {
   // Older WebKit browsers
   range = doc.createRange();
   range.setStart(sel.anchorNode, sel.anchorOffset);
   range.setEnd(sel.focusNode, sel.focusOffset);

   // Handle the case when the selection was selected backwards (from the end to the start in the
   // document)
   if (range.collapsed !== sel.isCollapsed) {
    range.setStart(sel.focusNode, sel.focusOffset);
    range.setEnd(sel.anchorNode, sel.anchorOffset);
   }
  }*/
 }
 return range;
}

function restoreSelection(range) {
 var win = getContentWindow();
 var doc = win.document;
 var sel = win.getSelection ? win.getSelection() : doc.selection;

 if (sel && range) {
  if (range.select) {
   range.select();
  } else if (sel.removeAllRanges && sel.addRange) {
   sel.removeAllRanges();
   sel.addRange(range);
  }
 }
}
