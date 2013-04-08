var key=0;

        function initchat()
        {
            var d=new Date;
            var xmlhttp=new XMLHttpRequest();
            var time=d.getTime();
            url="chat.jsp?init=true"+"&time="+time;
            xmlhttp.open("GET",url,false);
            xmlhttp.send();
            document.getElementById("onlinediv").innerHTML=xmlhttp.responseText;
            getchatlist();
            x=document.getElementById("chattingwith");
            n=x.options.length;
            for(i=0;i<n;i++)
            {
                    restorechat(x.options[i].value);
            }
            setTimeout("getallchat()",1000);
        }
        function restorechat(x)
        {
                var d=new Date;
		var xmlhttp=new XMLHttpRequest();
		var time=d.getTime();
		url="chat.jsp?startchat=true&to="+x+"&time="+time;
		xmlhttp.open("GET",url,false);
		xmlhttp.send();
                document.getElementById("chat").innerHTML+=xmlhttp.responseText;
        }
	function startchat(x)
	{
            y=document.getElementById("chattingwith");
            n=y.options.length;
            for(i=0;i<n;i++)
            {
                    if(y.options[i].value==x)
                        break;
            }
            if(i==n)
            {
                var d=new Date;
                var xmlhttp=new XMLHttpRequest();
                var time=d.getTime();
                url="chat.jsp?startchat=true&to="+x+"&time="+time;
                xmlhttp.open("GET",url,false);
                xmlhttp.send();
                document.getElementById("chat").innerHTML+=xmlhttp.responseText;
                n=document.getElementById("chattingwith").options.length;
                document.getElementById("chattingwith").options[n]=new Option(x,x);
            }
	}
	function sendchat(x,e)
	{
            key = e.keyCode;
            if (key == 13) {
            var d=new Date;
            var xmlhttp=new XMLHttpRequest();
            var time=d.getTime();
            url="chat.jsp?to="+x.nextSibling.value+"&msg="+x.value+"&time="+time;
            xmlhttp.open("GET",url,true);
            xmlhttp.send();
            }
	}

	function delnode(x)
	{
		for(i=0;i<document.getElementById("chattingwith").options.length;i++)
		{
			if(document.getElementById("chattingwith").options[i].value==x.childNodes[0].value)
                            {
                                var d=new Date;
                                var xmlhttp=new XMLHttpRequest();
                                var time=d.getTime();
                                url="chat.jsp?to="+x.childNodes[0].value+"&endchat=true"+"&time="+time;
                                xmlhttp.open("GET",url,false);
                                xmlhttp.send();

				document.getElementById("chattingwith").remove(i);
                            }

		}
		p = x.parentNode;
        p.removeChild(x);
	}

	function clearchat(x)
	{
            if (key == 13) {
                x.value = "";
            }

	}

        function getchatlist()
        {
            var s="";
            var d=new Date;
            var xmlhttp=new XMLHttpRequest();
            var time=d.getTime();
            url="chat.jsp?getlist=true"+"&time="+time;
            xmlhttp.onreadystatechange=function()
            {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        document.getElementById("onlinelist").innerHTML=xmlhttp.responseText;
                    }
            }
            xmlhttp.open("GET",url,true);
            xmlhttp.send();
            setTimeout("getchatlist();",30000);
        }
        function getallchat()
        {
                x=document.getElementById("chattingwith");
                n=x.options.length;
                
                for(i=0;i<n;i++)
                {
                        getchat(x.options[i].value);
                }
                
                getnewchat();
                setTimeout("getallchat();",1000);
        }

        function getnewchat()
        {
            var s="";
            var d=new Date;
            var xmlhttp=new XMLHttpRequest();
            var time=d.getTime();
            url="chat.jsp?checknew=true"+"&time="+time;
            xmlhttp.onreadystatechange=function()
            {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        s=xmlhttp.responseText;
                        var a=s.split("*",100);
                        if(a[1]!=null)
                            {
                            startchat(a[1]);
                            }
                    }
            }
            xmlhttp.open("GET",url,true);
            xmlhttp.send();
        }

	function getchat(t)
	{
            y=document.getElementById(t);
            var d=new Date;
            var xmlhttp=new XMLHttpRequest();
            var time=d.getTime();
            url="chat.jsp?to="+t+"&get=true"+"&time="+time;
            xmlhttp.open("GET",url,false);
            xmlhttp.send();
            y.previousSibling.innerHTML=xmlhttp.responseText;
            y.previousSibling.scrollTop=y.previousSibling.scrollHeight;
	}