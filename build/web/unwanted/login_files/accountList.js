
var tableObj = new Object();

function onClickRadio(hitID, tableID)
{
	var setID = hitID.substring(2,hitID.length);
	var rowID = "r" + setID;
	hiliteRow(rowID, tableID);
}

function changeRowColor(rowID,evnt, tableID){
	hiliteRow(rowID,tableID);
	
	if(evnt == "click"){
		var radioID = "rd" + rowID.substring(1,rowID.length);
		updateRadioButtons(radioID, tableID);
	}
}

function hiliteRow(rowID, tableID){
	tableObj = document.getElementById(tableID)
	var totRows = tableObj.rows;
	for (var i=0; i<totRows.length; ++i)
	{
		var trID = "r" + i;
		var trObj = new Object(document.getElementById(trID));
		var cName = trObj.className
		if( cName != 'selectedRow'){
			if(rowID == trID)
			{
				trObj.className = 'ActiveRow';
			}
			else
			{
				trObj.className = 'dActiveRow';
			}
		}
	}
}

function updateRadioButtons(radioID, tableID){
	tableObj = document.getElementById(tableID)
	var totRows = tableObj.rows;
	for (var i=0; i<totRows.length; ++i){
		var tempID = "rd" + i;
		var radioObj = new Object(document.getElementById(tempID));
		if(tempID == radioID){
			radioObj.checked="true";
			selectRow("r" + i, tableID);
		}
	}
}

function selectRow(rowID, tableID){
	tableObj = document.getElementById(tableID)
	var totRows = tableObj.rows;
	for (var i=0; i<totRows.length; ++i){
		var tempID = "r" + i;
		var trObj = new Object(document.getElementById(tempID));
		if(tempID == rowID){
			trObj.className = 'selectedRow';
		}else{
			trObj.className = 'dActiveRow';
		}
	}
}


function setDefaultColor(rowID){
	var trObj = new Object(document.getElementById(rowID));
	var cName = trObj.className;
	if( cName != 'selectedRow'){
		trObj.className = 'dActiveRow';
	}
}