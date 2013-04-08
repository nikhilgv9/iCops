function deletemsg(){
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        var x="";
        for(var i=0;;i++)
        {
            chek=document.getElementById("chk"+i);
            if(chek==null) break;
            if(chek.checked==true)
            x+=chek.value+"/";
        }
        url="getmsg.jsp?"+"delete="+x+"&time="+time;
        xmlhttp.open("GET",url,false);
        xmlhttp.send();
    }
    function deleteforever(){
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        var x="";
        for(var i=0;;i++)
        {
            chek=document.getElementById("chk"+i);
            if(chek==null) break;
            if(chek.checked==true)
            x+=chek.value+"/";
        }
        url="getmsg.jsp?"+"deleteforever="+x+"&time="+time;
        xmlhttp.open("GET",url,false);
        xmlhttp.send();
    }
    function restoremsg(){
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        var x="";
        for(var i=0;;i++)
        {
            chek=document.getElementById("chk"+i);
            if(chek==null) break;
            if(chek.checked==true)
            x+=chek.value+"/";
        }
        url="getmsg.jsp?"+"restore="+x+"&time="+time;
        xmlhttp.open("GET",url,false);
        xmlhttp.send();
    }
    function selectAll()
    {
        for(var i=0;;i++)
        {
            chek=document.getElementById("chk"+i);
            if(chek==null) break;
            document.getElementById("chk"+i).checked=true;
        }
    }
    function save(){
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        var msg=document.getElementById("edit").contentWindow.document.body.innerHTML;
        for(;;)
        {
        prev=msg;
        msg=msg.replace("&","..amb");
        msg=msg.replace(";","..sem");
        /*msg=msg.replace('\n', ' ');
        msg=msg.replace('\r', ' ');*/
        if(prev==msg) break;
        }
        url="composemail.jsp?send=false"+"&to="+document.getElementById("to").value+"&sub="+
            document.getElementById("sub").value+"&msg="+msg+
            "&time="+time;
        xmlhttp.open("GET",url,false);
        xmlhttp.send();
        document.getElementById("msgout").innerHTML=xmlhttp.responseText;
    }
    function send(){
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        var msg=document.getElementById("edit").contentWindow.document.body.innerHTML;
        for(;;)
        {
        prev=msg;

        msg=msg.replace("&","..amb");
        msg=msg.replace(";","..sem");
        /*msg=msg.replace('\n', ' ');
        msg=msg.replace('\r', ' ');*/

        if(prev==msg) break;
        }
        url="composemail.jsp?send=true"+"&to="+document.getElementById("to").value+"&sub="+
            document.getElementById("sub").value+"&msg="+msg+
            "&time="+time;
        xmlhttp.open("GET",url,false);
        xmlhttp.send();
        document.getElementById("msgout").innerHTML=xmlhttp.responseText;
    }
    function reply()
    {
        to=document.getElementById("reply").value;
        compose();
        to="";
    }
    function forward()
    {
        fwd=document.getElementById("fwd").value;
        sub=document.getElementById("subject").value;
        compose();
        sub="";
        to="";
    }
    function getmsg(x,sort,page) {
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        url="getmsg.jsp?"+"get="+x+"&sortby="+sort+"&page="+page+"&time="+time;
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                document.getElementById("msgdiv").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET",url,true);
        xmlhttp.send();

    }
    function prevnext(x,page)
    {
        document.getElementById("pageno").value=page;
        getmsg(x,document.getElementById("Select1").value,page);
    }
    function openmsg(x,open)
    {
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        url="getmsg.jsp?open="+open+"&no="+x+"&time="+time;
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                document.getElementById("msgdiv").innerHTML=xmlhttp.responseText;
            }
        }
        xmlhttp.open("GET",url,true);
        xmlhttp.send();
    }
    function init(x)
    {
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        url="getmsg.jsp?box="+x+"&time="+time;
        xmlhttp.open("GET",url,false);
        xmlhttp.send();
        document.getElementById("msgout").innerHTML=xmlhttp.responseText;
    }
    function geteditor()
    {
        var d=new Date;
        var xmlhttp=new XMLHttpRequest();
        var time=d.getTime();
        url="composemail.jsp?compose=true"+"&time="+time;
        xmlhttp.open("GET",url,false);
        xmlhttp.send();
        document.getElementById("msgout").innerHTML=xmlhttp.responseText;
    }
    function load(x)
    {
        document.getElementById("navMessages").className='act';
        if(x=='compose') compose();
        else{
        to="";
        sub="";
        fwd="";
        highlight(x);
        init(x);
        getmsg(x,document.getElementById("Select1").value,document.getElementById("pageno").value);
        }
    }
    function compose()
    {
        geteditor();
        highlight("compose");
        window.setTimeout("Start()",1000);
        window.setTimeout("loadtext()",1200);
        document.getElementById("to").value=to;
        document.getElementById("sub").value=sub;
    }
    function loadtext()
    {
        document.getElementById("edit").contentWindow.document.body.innerHTML=fwd;
    }
    function highlight(x)
    {
        document.getElementById("inbox").style.fontWeight="normal";
        document.getElementById("compose").style.fontWeight="normal";
        document.getElementById("sent").style.fontWeight="normal";
        document.getElementById("drafts").style.fontWeight="normal";
        document.getElementById("trash").style.fontWeight="normal";
        document.getElementById(x).style.fontWeight="bold";
    }

