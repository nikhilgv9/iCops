
//var regExp = /<\/?[^>]+>/gi;
 
function ReplaceTags(xStr){
	xStr = xStr.replace(regExp,"");
	return xStr;
} 

function hiliteNavLink(linkID){


	
	
	var isValid = true;
	
	var linkObj = new Object(document.getElementById("leftLink" + linkID));
	
	
	var tempStr = linkObj.innerHTML;
	
	
	if(tempStr == undefined || tempStr == null){
		isValid = false;
	}
	if(isValid){
		var strLinkText = ReplaceTags(tempStr);
	
		linkObj.className = 'leftNavHilite';		
	
		linkObj.innerHTML = '<b>' + tempStr + '</b>';
		
	}
   else{
		alert("Error: Specified link number ' " + linkID + " ' not found.\r\n\r\nPlease check the ' LeftNav_HiliteLink ' template property value using\r\nModify > Template Properties in Dreamweaver.")
	}
}

function hiliteSecondNavTab(tabNum){

	var isTab = true;

	var tdObj = new Object(document.getElementById("tab" + tabNum + "_left"));
	var tempStr = tdObj.innerHTML;

	if(tempStr == undefined || tempStr == null){
		isTab = false;
	}
	if(isTab){
		tdObj.innerHTML = '<img src="/sbijava/images/2ndLevelNav_03.jpg" alt="" width="15" height="33">';

		tdObj = new Object(document.getElementById("tab" + tabNum + "_mid"));
		tdObj.className = 'secondLevelSelected';
		tdObj.innerHTML = ReplaceTags(tdObj.innerHTML);

		tdObj = new Object(document.getElementById("tab" + tabNum + "_right"));
		tdObj.innerHTML = '<img src="/sbijava/images/2ndLevelNav_05.jpg" alt="" width="15" height="33">';
		
	}else{
		alert("Error: Specified tab ID ' " + tabNum + " ' not found.\r\n\r\nPlease check the ' SecondNav_HiliteTab ' template property value using\r\nModify > Template Properties in Dreamweaver.")
		}
}

function hiliteOn(td_ID){
	var tdParam = td_ID.split("_");
	var tempID = Number(tdParam[1]);
	document.getElementById(td_ID).className='TDhilite';
	if(tempID == 6){
		document.getElementById("FNav_7").className='TDhilite';
	}else if(tempID == 7){
		document.getElementById("FNav_6").className='TDhilite';
	}
}

function hiliteOff(td_ID){
	var tdParam = td_ID.split("_");
	var tempID = Number(tdParam[1]);
	document.getElementById(td_ID).className='TDplain';
	if(tempID == 6){
		document.getElementById("FNav_7").className='TDplain';
	}else if(tempID == 7){
		document.getElementById("FNav_6").className='TDplain';
	}
}

function hiliteTop(td_ID){
	if(Number (td_ID) > 0){
		var linkObj = new Object(document.getElementById("top_" + td_ID))
			linkObj.className='homeToplinksbgHilite';
		if(td_ID == 1){
			document.getElementById("top_1img").src='/sbijava/images/header_2_04_hilite.jpg';
		}

		var tempStr = linkObj.innerHTML;
		var strLinkText = ReplaceTags(tempStr);
		linkObj.innerHTML = '<span class="topNavHilited">' + strLinkText + "</span>";
	}
}


function hiliteLocateUsLink(linkID){
	if(Number(linkID) > 0){

		var isValid = true;

		var linkObj = new Object(document.getElementById("locateUS" + linkID));

		var tempStr = linkObj.innerHTML;

		if(tempStr == undefined || tempStr == null){
			isValid = false;
		}
		if(isValid){
			var strLinkText = ReplaceTags(tempStr);

			linkObj.className = 'locateUsLinksHilite';
			linkObj.innerHTML = '<span class="locateUsLinksHilite">' + strLinkText + "</span>";
		}
	   else{
			alert("Error: Specified link number ' " + linkID + " ' not found.\r\n\r\nPlease check the ' LocateUs_Hilite ' template property value using\r\nModify > Template Properties in Dreamweaver.")
		}
	}
}


function setFocus(theID){
	document.getElementById(theID).focus();
}

 
function disableSubmitButton(formObj,id){
	formObj.submit();
	if(document.getElementById(id)!=null)
		document.getElementById(id).disabled=true;
}
