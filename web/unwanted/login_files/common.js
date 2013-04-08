
  /* CR 2614 change starts*/
function disableautocompletion(id1){ 
	 var passwordControl=document.getElementById(id1);
	 passwordControl.setAttribute("autocomplete","off");
}
/* CR 2614 change ends*/ 

function checkSpecial(str){
	var specialChars = "`=[]\\;'~!@$%^&*()+{}|:\"<>?";
	for(var i=0; i<specialChars.length; i++){
			if(str.indexOf(specialChars.substr(i, 1)) >= 0){
			 return false;
			}else{
				continue;
			}
		}
		return true;
 
}
  







function selectAddress(id,frm)
{
   	if(id=="courier")
	{
	   if(document.getElementById("modeOfDelivery")!=null)
		{
		document.getElementById("modeOfDelivery").value="0";
		}
		document.getElementById("dlvadd").style.display="";
		
		document.getElementById("radd").checked=true;
		document.getElementById("regadd").style.display="";
		document.getElementById("newadd").style.display="none";
		document.getElementById("strAdress1").value="";
 		document.getElementById("strAdress2").value="";
 		document.getElementById("cty").value="";
 		document.getElementById("ste").value="";
 		document.getElementById("pn").value="";
                 
	}
	else if(id=="post")
	{
	   if(document.getElementById("modeOfDelivery")!=null)
		{
		document.getElementById("modeOfDelivery").value="1";		
		}
		document.getElementById("dlvadd").style.display="";
		
		//document.getElementById("radd").checked=true;//commented on 28-08-2009
		document.getElementById("regadd").style.display="";
		document.getElementById("newadd").style.display="none";
		document.getElementById("strAdress1").value="";
 		document.getElementById("strAdress2").value="";
 		document.getElementById("cty").value="";
 		document.getElementById("ste").value="";
 		document.getElementById("pn").value="";
	        	
	
	}else if(id=="colper") 
	{
	   if(document.getElementById("modeOfDelivery")!=null)
		{
		document.getElementById("modeOfDelivery").value="2";
		}
	  	document.getElementById("dlvadd").style.display="none";
	  
	  	//document.getElementById("radd").checked=false;//commented on CR 28-08-2009
	  	//document.getElementById("nadd").checked=false;//commented on CR 28-08-2009
	  	document.getElementById("strAdress1").value="";
 		document.getElementById("strAdress2").value="";
 		document.getElementById("cty").value="";
 		document.getElementById("ste").value="";
 		document.getElementById("pn").value="";
 		document.getElementById("address1").value="";
 		document.getElementById("address2").value="";
 		document.getElementById("city").value="";
 		document.getElementById("pin").value="";
 		document.getElementById("state").value="";
 		document.getElementById("country").value="";
 		
	}
	
	else if(id=="self")
	{
		if(document.getElementById("modeOfDelivery")!=null)
		{
		document.getElementById("modeOfDelivery").value=1;
		}
	  	document.getElementById("dlvadd").style.display="none";
	}
	
		
	else if(id=="radd")
	{
	 	
	 	document.getElementById("regadd").style.display="";
 		document.getElementById("newadd").style.display="none";
 		
 		document.getElementById("strAdress1").value="";
 		document.getElementById("strAdress2").value="";
 		document.getElementById("cty").value="";
 		document.getElementById("ste").value="";
 		document.getElementById("pn").value="";
 		
 		
	}
	else if(id=="nadd")
	{
	 	document.getElementById("regadd").style.display="none";
		document.getElementById("newadd").style.display="";
	}
	else if(id=="25" || id=="50" || id=="100")
	{
		document.forms[frm].noOfLeaves.value=id;
	}
}

function selectPaymentMode(mode)
{
    	
	document.getElementById("tans").value=mode;
	 	
 	if(mode=="TFB")
	{ 
	 document.getElementById("TFB").style.display="";
	 document.getElementById("TFB1").style.display="";
	 document.getElementById("DD").style.display="none";
	 document.getElementById("ddTransfer").value="1";
	 
	 document.getElementById("favouring").value="";
	 
	}
	else if(mode=="DD")
	{
	 document.getElementById("TFB").style.display="none";
	 document.getElementById("TFB1").style.display="none";
	 document.getElementById("DD").style.display="";
	 document.getElementById("ddTransfer").value="0";
	}
}

function addressValidation(frm)
{
 var street=rTrim(document.forms(frm).strAdress1.value);
 var city=rTrim(document.forms(frm).cty.value);
 var state=rTrim(document.forms(frm).ste.value);
 var pin=rTrim(document.forms(frm).pn.value);
 var country=rTrim(document.forms(frm).ctry.value);

 var flag=false; 
 var streetFlag=false;
 var cityFlag=false;
 var stateFlag=false;
 var pinFlag=false;
 var countryFlag=false;

 if(street=="" && street.length==0)
 {
   alert("Enter street address");
   document.forms(frm).strAdress1.focus();
   return false;
  } 
 else{
  
	  if(!checkSpecial(street)) {
		  alert("Special characters are not allowed");
		  document.forms(frm).strAdress1.focus();
		  document.forms(frm).strAdress1.select();
		  return false;
	  
	  }else{
  streetFlag=true;  
  }
  
  	
  }
 if(city==""){
    alert("Enter the city");
   document.forms(frm).cty.focus();
    return false;
   }
 else{
 	 if(!checkSpecial(city)) {
		  alert("Special characters are not allowed");
		  document.forms(frm).cty.focus();
		  document.forms(frm).cty.select();
		  return false;
	  
	  }else{
  cityFlag=true;    
	  }
 
 }
 
    
 if(state==""){
   alert("Enter the state ");
    document.forms(frm).ste.focus();
   return false;
   }
 else{
 	 if(!checkSpecial(state)) {
		  alert("Special characters are not allowed");
		  document.forms(frm).ste.focus();
		  document.forms(frm).ste.select();
		  return false;
	  
	  }else{
   stateFlag=true;    
	  }
    
 }
  
 if(pin==""){
   alert("Enter pincode"); 
    document.forms(frm).pn.focus();     
return false;
}
  else  
  {
   for(i=0;i<pin.length;i++)
   {
     if(pin.charAt(i)>="0" && pin.charAt(i)<="9")
      pinFlag=true;    
     else
     {
      pinFlag=false;
      alert("Enter valid pincode");
     
      document.forms(frm).pn.focus();
       document.forms(frm).pn.select()
      break;
     } 
   }
  }  

if(pin.length<6){
	alert("Pincode should be six digits");
	document.forms(frm).pn.focus();
    document.forms(frm).pn.select()
    return false;
  
}

 
 if(country=="" || country.length==0){
 
   alert("Enter Country");
   document.forms(frm).ctry.focus();
   return false;
   }
 else{
 	 if(!checkSpecial(country)) {
		  alert("Enter valid country");
		  document.forms(frm).ctry.focus();
		  document.forms(frm).ctry.select();
		  return false;
	  
	  }else{
   countryFlag=true;     
	  }
 }
 	
 
     
 
 if(streetFlag && cityFlag && stateFlag && pinFlag && countryFlag)
 {
  flag=true;

  }

  return flag;
}

function onSubmitCheckbook(frm)
{
 var flag=false;
 var flag1=false;
 var flag2=false;
 
 if(document.getElementById("accountNo").value!="")
   flag1=true;
 else{
   alert("Select an account number");
   return false;
   }
//Commented on 28-08-2009
 //if(document.forms[frm].post.checked ||document.forms[frm].courier.checked )
if(document.forms[frm].post.checked )
 {
	//commented on CR 28-08-2009
 	/*if(document.forms[frm].radd.checked)
 	{*/
	 	flag2=true;
	 	
	 	var address1=document.getElementById("raddress").innerHTML;
	 	var city=document.getElementById("rcty").innerHTML;
	 	var state=document.getElementById("rstate").innerHTML;
	 	var pin=document.getElementById("rpn").innerHTML;
	 	var country=document.getElementById("rctry").innerHTML;
	 	
 		document.getElementById("address1").value=address1;
 		document.getElementById("city").value=city;
 		document.getElementById("state").value=state;
 		document.getElementById("pin").value=pin;
 		document.getElementById("country").value=country;
 	/*}
 	else
 	{
 	    flag2=addressValidation(frm);
 		document.getElementById("address1").value=document.forms[frm].strAdress1.value;
 		document.getElementById("address2").value=document.forms[frm].strAdress2.value;
 		document.getElementById("city").value=document.forms[frm].cty.value;
 		document.getElementById("state").value=document.forms[frm].ste.value;
 		document.getElementById("pin").value=document.forms[frm].pn.value;
 		document.getElementById("country").value=document.forms[frm].ctry.value;
 
 	}	*/  
 }
 else
 {
  	flag2=true;
 }	
 if(flag1 && flag2)
  	flag=true;

 return flag;
}

function selectAccountNo(nickName,ano,aType,bCode,accId,accTypeId,branchCodeId,selType)
{
	 if(accId!="" )
	 {
	 	document.getElementById(accId).value=ano;
	 }
	 if(accTypeId!="")
	 {
	 	document.getElementById(accTypeId).value=aType;
	 }
	 if( branchCodeId!="")
	 {
	 	document.getElementById(branchCodeId).value=bCode;
	 }
	 	document.getElementById("showSel"+selType).style.display="";
	 	document.getElementById("sel"+selType).innerHTML=nickName;
	 
	
	 
} 



function changeButton(bname,transOption)
{
	document.getElementById("stinbtn").name=bname;
	document.getElementById("standingInstructionsType").value=transOption;
	document.getElementById("transOption").value=transOption;
	
	
}


 


function validateTransfers(acctNo,creditAccountType)
{
	var accountNature = getAccountNature(acctNo);
	var validTransaction = validateTransfer(accountNature,creditAccountType,'Pri');

	if (validTransaction == 'Warning')
	{
		if (confirm("You are transfering repatriable money into a non-repatriable account. This transfer cannot be reversed. Do you want to continue?"))
			validateFlag = true;
		else
			validateFlag = false;
	}
	else if (validTransaction == 'Yes')
		validateFlag = true;
	else
	{
		alert("You cannot transfer funds from " +accountNature + " account to "+ creditAccountType + " account." );
		validateFlag = false;		
	}	
	return validateFlag;
}


function standOnSubmitValidate6()
{
	var flag=false;
	var flag1=false;
	var flag2=false;
	var flag3=false;
	var flag4=false;
	var flag5=false;
	var flag6=false;
	var flag7=false;

	var str=document.getElementById("startdate").value; 
	var str1=str.split('/');
	var str2=str1[0] + " " + str1[1] + " " + str1[2];
	var str3=document.getElementById("enddate").value; 
	var str4=str3.split('/')
	var str5=str4[0] + " " + str4[1] + " " + str4[2];
	
	var acno = document.getElementById("accountNo").value;
	var brcode=document.getElementById("branchCode").value;
	var crno = document.getElementById("creditAccountNo").value;
	var crbrcode=document.getElementById("creditBranchCode").value;

if(document.getElementById("frequency")!=null)
  document.getElementById("frequency").value = document.getElementById("freq").value;


	if(acno == "" || acno==null )
	{
		 alert("Select an account number");
		 return false;
	}
	else
	 	flag1=true;

	 flag2=validatemain('stndins','amount.amtcheck.amount.amount');
	 if (!flag2){
	 	return false;
	 }
	 

	 if(str1[0]=="dd-mmm-yyyy" || str3[0]=="dd-mmm-yyyy")
	 {
		  alert("Select Start and End Date");
		  return false;
	 } 
	  else{
			   // added by saravanan 23-01-2006
		 	if(str3 == "dd-mmm-yyyy" ){
		 		alert("Select end date");
		 		return false;
			 }else{
		  flag3=dateValidation(str2,str5);
 if (!flag3){
	 	return false;//Added for CR-2397
	 }

		 }
		  }
	 //end by saravanan 23-01-06

	if(crno == "" || crno == null )
	{
		 alert("Select a credit account number to proceed");
		 return false;
	}
	else
		 flag4=true;
	 
		 var cractype=getAccountNature(crno);
	 	 flag5=validateTransfers(acno,cractype)
 
 	if(acno==crno)
 	{
	 	 alert("Debit and credit accounts cannot be the same");
	 	 return false;
 	}
 	else
 	 	flag6=true;
 	 
 	if(brcode != crbrcode)
 	{
	 	 alert("Only intra branch transfers is allowed");
	 	 return false;
 	} 
 	else
 	 	flag7=true;
 	 
	if(flag1 && flag2 && flag3 && flag4 && flag5 && flag6 && flag7)
	{
		 flag=true;
	} 
	
document.stndins.action="smsenablehighsecurity.htm";//Added for CR-2397
return flag;	
}



function standOnSubmitValidate5()
{
	var flag=false;
	var flag1=false;
	var flag2=false;
	var flag3=false;
	var flag4=false;
	var flag5=false;
	var flag6=false;


	
	document.getElementById("location").value=document.getElementById("locationName").value;
	
	if(rTrim(document.getElementById("favouring").value)==null || rTrim(document.getElementById("favouring").value)=="")
	{
		alert("Enter the name in favour of whom the Demand Draft is drawn");
		document.getElementById("favouring").focus();
		return false;
	}
	else
	{
	 flag2=true;
	}
	
	if(rTrim(document.getElementById("locationName").value)!="--Select Location--")
	{
	 flag4=true;
	} 
	else{
		alert("Select location");
		document.getElementById("locationName").focus();
		 return false;
	 }
	 
	if(rTrim(document.getElementById("branchName").value)!="--Select Branch--")
	{
	 flag5=true;
	} 
	else
	{
	 alert("Select branch");
	document.getElementById("branchName").focus();
	return false;
	 }
	
	
	if(document.getElementById("courier").checked)
	 {
	    
	 	if(document.getElementById("radd").checked)
	 	{
	
		 	flag6=true;
		 	
	 		document.getElementById("address1").value=document.getElementById("raddress").innerHTML;
	 		document.getElementById("city").value=document.getElementById("rcty").innerHTML;
	 		document.getElementById("state").value=document.getElementById("rstate").innerHTML;
			document.getElementById("pin").value=document.getElementById("rpn").innerHTML;
			document.getElementById("country").value=document.getElementById("rctry").innerHTML;
	
	
	 	 	
	 	}
	 	else
	 	{
	 	    flag6=addressValidation("stndins");
	 		document.getElementById("address1").value=document.getElementById("strAdress1").value;
	 		document.getElementById("address2").value=document.getElementById("strAdress2").value;
	 		document.getElementById("city").value=document.getElementById("Cty").value;
	 		document.getElementById("state").value=document.getElementById("ste").value;
	 		document.getElementById("pin").value=document.getElementById("pn").value;
	 		document.getElementById("country").value=document.getElementById("ctry").value;
	 		
	 	}	  
	 }
	 else
	 {
	  	flag6=true;
	 }
	
	if(flag2  && flag4 && flag5 && flag6)
	 	flag=true;
	 	
	return flag;

}
 

function standOnSubmitValidate4(acno,brcode)
{
	var flag=false;
	var flag1=false;
	var flag2=false;
	var flag3=false;
	var flag4=false;
 
 if(document.getElementById("tans").value=='TFB')
 {

 	var crno = document.getElementById("creditAccountNo").value;
	var crbrcode=document.getElementById("creditBranchCode").value;

    flag1=standOnSubmitValidate2();

    if( flag1 == false){
    	return false;
    }
    
    var cractype=getAccountNature(crno);

 	flag2=validateTransfers(acno,cractype)
 
 	if(acno==crno)
 	{
 		 alert("Debit and credit accounts cannot be the same");
return false;//CR-2397
 	}
 	else
 		 flag3=true;
 	 
 	if(brcode != crbrcode)
 	{
 		 alert("Only intra branch transfers is allowed");
return false;//CR-2397
 	} 
 	else
 	 flag4=true;
 	 
 	if (flag1 && flag2 && flag3 && flag4)
 		flag=true;
 
 }   
 else
 {
    if(document.getElementById("creditAccountNo")!=null)
    {
		document.getElementById("creditAccountNo").value="";
		document.getElementById("creditBranchCode").value="";
	}	
    	flag=standOnSubmitValidate5();	  
 }  
 document.stndins.action="smsenablehighsecurity.htm";//Added for CR-2397
return flag;	
}


function standOnSubmitValidate2()
{
 var flag=false;
 var acno=document.getElementById("creditAccountNo").value;
 if(acno=="" || acno==null){
  alert("Select an account number");
  	 return false;
  }
 else{
  	flag=true;
  	}
  return flag;
}



function standOnSubmitValidate1()
{

	 var validate=false;
	 
	 var flag2=false;
	 var acno=document.getElementById("accountNO").value;
	 if(acno=="" || acno==null){
		  alert("Select an account number");	  
		  return false;
	  }
	 else
		  flag2=true;
	 
 if(!validatemain('stndins','amount.amtcheck.amount.amount')){
  document.stndins.amount.focus();
	 	return false;
 }
 
 	
	
	 if(document.getElementById("frequency")!=null)
		document.getElementById("frequency").value = document.getElementById("freq").value;
	
	 var str=document.getElementById("startdate").value; 
	
	 var str1=str.split('/');
	 var str2=str1[0] + " " + str1[1] + " " + str1[2];
	
	 if(document.getElementById("enddate")!=null)
	 {
		 var str3=document.getElementById("enddate").value; 
		 var str4=str3.split('/')
		 var str5=str4[0] + " " + str4[1] + " " + str4[2];
	 }
	 var flag=false;
	
	 if(document.getElementById("enddate")!=null)
	 {
		 if(str1[0]=="dd-mmm-yyyy" || str3[0]=="dd-mmm-yyyy")
		 {
		  	alert("Select Start and End Date");
		  	document.getElementById("startdate").focus();
		  	return false;
		 }
		 else{
			   // added by saravanan 9-01-2006
		 	if(str3 == "dd-mmm-yyyy" ){
		 		alert("Select end date");
		 		return false;
			 }else{
			  flag=dateValidation(str2,str5);
		 }
		  }
	 }
	 else
	 {
		  if(str1[0]=="dd-mmm-yyyy"){
			 	alert("Select start date");
			 	document.getElementById("startdate").focus();
				return false;
		  }else{
		  		
		      	var dte=new Date();
 				var tdate=new Date(dte.toString().substring(4,10)+" "+ dte.getFullYear());
		    	
			    flag=dateValidation1(tdate,str2);
		  }     	  
	 }
	 
	 if(flag && flag2)//CR-2397
	 {
	  	validate=true;
	 }
	 return validate;
	}



function rTrim(value)
{
	 var val=value;
	 for(i=0;i<value.length;i++)
	 {
	  if(value.charAt(i)==" ")
	  {
	   val=val.replace(" ", "");
	  }
	  else
	   break;
	 }
	 return val;
}






function dateValidation(startDate,endDate)
{
	var flag = false;
	
	var dte=new Date();
 	var tdate=new Date(dte.toString().substring(4,10)+" "+ dte.getFullYear());
	var sdate=new Date(startDate);

	var edate=new Date(endDate);
	
	
	if(sdate >= tdate)
	{
		if(edate > sdate)
		{
		   flag=true;
		}   
		else
		{
		   alert("End date cannot be before the start date");
		   flag=false;
		}   
	}
	else
	{
		alert("Start date can be the current date or a later date");  
		flag=false;//CR-2397
		} 
		
  return flag;   
}

 

function displayNo(accno,id)
{
 document.getElementById("sel"+id).innerHTML=accno;
 
}
 



function dateValidation1(startDate,endDate)
{
	var flag = false;
		
	var edate=new Date(endDate);	
	
	if(edate>=startDate)
	{
	   flag=true;
	}   
	else
	{
	   alert("Start date can be the current date or a later date");
	   flag=false; 
	}   
	   
  return flag;   
}
  
function getBankSystem(bankSystem){
	if(bankSystem=='Core'){
		document.getElementById("nonCore").style.display="none";
		document.getElementById("core").style.display="";
	}
	if(bankSystem=='NonCore'){
		document.getElementById("ttype").disabled=false;
		document.getElementById("nonCore").style.display="";
		document.getElementById("core").style.display="none";
	}
}  

function setBankSystem(prodDesc,bankSys,contName,prodCode) // Argument prodCode added for CR 1256
{
	
	if(document.getElementById(contName+"productDisc")!=null){
		document.getElementById(contName+"productDisc").value=prodDesc;
		//CR-2452 Begins
		if( document.getElementById("debitProductDesc") !=null){
			document.getElementById("debitProductDesc").value=prodDesc;
			
		}
		//CR-2452 End
		//Cr-2734
		if( document.getElementById("debitProductCode") !=null){
			document.getElementById("debitProductCode").value=prodCode;
		}
	}
	if(document.getElementById(contName+"bankSystem")!=null)
		document.getElementById(contName+"bankSystem").value=bankSys;
	//	Added for CR 1256
	if(document.getElementById(contName+"productCode")!=null)
		document.getElementById(contName+"productCode").value=prodCode;	
	//	End for CR 1256	
}
//	Added for CR 2830
function validateTransfersForAccount(strDebitAcc,creditAccountType,debitBankSys,debitProdDesc)
{	
	if(debitBankSys != ""){
		if(debitBankSys=='NonCore')
			strDebitAccNature = getAccountNature(strDebitAcc);
		else
			strDebitAccNature = getAccountNatureCore(debitProdDesc);
	}	
	
	
	var validTransaction = validateTransfer(strDebitAccNature,creditAccountType,'Pri');
	if (validTransaction == 'Warning')
	{
		if (confirm("You are transfering repatriable money into a non-repatriable account. This transfer cannot be reversed. Do you want to continue?"))
			validateFlag = true;
		else
			validateFlag = false;
	}
	else if (validTransaction == 'Yes')
		validateFlag = true;
	else
	{
		alert("You cannot transfer funds from " +accountNature + " account to "+ creditAccountType + " account." );
		validateFlag = false;		
	}	
	return validateFlag;
}
//	End of CR 2830