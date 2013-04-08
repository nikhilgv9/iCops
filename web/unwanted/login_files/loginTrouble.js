// JavaScript Document
//
function submitLogin()
{
	var username = document.quickLookForm.userName.value;
	var password= document.quickLookForm.password.value;
	var regexp = new RegExp("\\d{19}");
	if(username==""){
		alert("Enter username");
		document.quickLookForm.userName.focus();
		return false;
	}
	else if(password=="")
	{
		alert("Enter password");
		document.quickLookForm.password.focus();
		return false;
	}  
	else{
		if( password.length>20){
			alert("Password length should not be more than 20 characters");
			document.quickLookForm.password.value="";
			document.quickLookForm.password.focus();
			return false;
		}

        document.getElementById("Button2").disabled=true;
		if(username.match(regexp))
		{
			document.quickLookForm.action="geuser.htm"
			document.quickLookForm.submit();
		}else{
			encrypt();			
			document.quickLookForm.action="loginsubmit.htm"
			document.quickLookForm.submit();
		}
	}
	return true;
}


function submitRSupportLogin(){
var username = document.quickLookForm.userName.value;
	var password= document.quickLookForm.password.value;
	var regexp = new RegExp("\\d{19}");
	if(username==""){
		alert("Enter username");
		document.quickLookForm.userName.focus();
		return false;
	}
	else if(password=="")
	{
		alert("Enter password");
		document.quickLookForm.password.focus();
		return false;
	}  
	else{
		if( password.length>20){
			alert("Password length should not be more than 20 characters");
			document.quickLookForm.password.value="";
			document.quickLookForm.password.focus();
			return false;
		}

        document.getElementById("Button2").disabled=true;
		if(username.match(regexp))
		{
			document.quickLookForm.action="geuser.htm"
			document.quickLookForm.submit();
		}else{
			encrypt();	
											
			document.quickLookForm.action="userloginsubmit.htm"
			document.quickLookForm.submit();
		}
	}
	return true;
}

var troubleID = new String();
function showForm()
{
	var pos = troubleID.substr(3,1);
	var fname = "Login_Trouble" + pos + ".html";
	document.location.href = fname;
}

function submitPPK()
{
	var username = document.ppkForm.userName.value;
	if(username==""){
		alert("Enter username");
		document.ppkForm.userName.focus();
		return false;
	 }
	document.ppkForm.action="loginppksubmit.htm"
	document.ppkForm.submit();
}//Corporte support login validation.

function submitSupportLogin()
{
var username = document.quickLookForm.userName.value;
	var password= document.quickLookForm.password.value;
	var regexp = new RegExp("\\d{19}");
	if(username==""){
		alert("Enter username for Support");
		document.quickLookForm.userName.focus();
		return false;
	}
	else if(password=="")
	{
		alert("Enter password");
		document.quickLookForm.password.focus();
		return false;
	}  
	else{	
		if(username.match(regexp))
		{
			document.quickLookForm.action="geuser.htm"
			document.quickLookForm.submit();
		}else{
			//encrypt();			
			document.quickLookForm.action="supportloginsubmit.htm"
			document.quickLookForm.submit();
		}
	}
	return true;
}


//Function for disabling cntrl key combinations
function disableCtrlKeyCombination(e)
{
        //list all CTRL + key combinations you want to disable
        var forbiddenKeys = new Array('a','n','c','x','v','j');
        var key;
        var isCtrl;

        if(window.event)
        {
                key = window.event.keyCode;     //IE
                if(window.event.ctrlKey)
                        isCtrl = true;
                else
                        isCtrl = false;
        }
        else
        {
                key = e.which;     //firefox
                if(e.ctrlKey)
                        isCtrl = true;
                else
                        isCtrl = false;
        }

        //if ctrl is pressed check if other key is in forbidenKeys array
        if(isCtrl)
        {
                for(i=0; i<forbiddenKeys.length; i++)
                {
                        //case-insensitive comparation
                        if(forbiddenKeys[i].toLowerCase() == String.fromCharCode(key).toLowerCase())
                        {

                                return false;
                        }
                }
        }
        return true;
}