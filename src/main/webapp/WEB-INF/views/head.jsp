<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="language" content="en" />

    <title>OpenClinica Rule Designer</title>

    <link rel="icon" type="image/ico" href="images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="includes/css/header.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/reset.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/text.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/960.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/layout.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/nav.css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/ie6.css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/ie.css" media="screen" /><![endif]-->
    <link rel="stylesheet" type="text/css" href="includes/layout-default-latest.css" />
    <!--<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery.ui.all.css" />-->
    <link rel="stylesheet" type="text/css" href="includes/jquery-ui-1.8.6.custom/css/custom-theme/jquery-ui-1.8.6.custom.css" /> 
    <link rel="stylesheet" type="text/css" href="includes/tipTipv13/tipTip.css"/>
    <link rel="stylesheet" type="text/css" href="includes/styles.css"/>
    <style type="text/css">@import "includes/jquery.countdown.package-1.5.9/jquery.countdown.css";</style> 
    <!-- CUSTOMIZE/OVERRIDE THE DEFAULT CSS -->

    <style type="text/css">

    /* remove padding and scrolling from elements that contain an Accordion OR a content-div */
    .ui-layout-center , /* has content-div */
    .ui-layout-west ,   /* has Accordion */
    .ui-layout-east ,   /* has content-div ... */
    .ui-layout-east .ui-layout-content { /* content-div has Accordion */
        padding: 0;
        overflow: hidden;
    }
    .ui-layout-center P.ui-layout-content {
        line-height:    1.4em;
        margin:         0; /* remove top/bottom margins from <P> used as content-div */
    }
    h3, h4 { /* Headers & Footer in Center & East panes */
        font-size:      1.1em;
        background:     #EEF;
        border:         1px solid #BBB;
        border-width:   0 0 1px;
        padding:        7px 10px;
        margin:         0;
    }
    .ui-layout-east h4 { /* Footer in East-pane */
        font-size:      0.9em;
        font-weight:    normal;
        border-width:   1px 0 0;
    }
    h1 {
    color: #D4A718;
    font-family: Tahoma,Arial,Helvetica,sans-serif;
    font-size: 18px;
    font-weight: bold;
    line-height: 24px;
    }
    a.textcolor:active, a.textcolor:hover, a.textcolor:link, a.textcolor:visited{
        color: #CCC;
    }
    
    
    
    </style>

    <!-- REQUIRED scripts for layout widget -->
    <script type="text/JavaScript" language="JavaScript" src="includes/designer.js"></script>
    <script type="text/JavaScript" language="JavaScript" src="includes/jquery-1.4.2.min.js"></script>
    <script type="text/JavaScript" language="JavaScript" src="includes/jquery-ui-1.8.6.custom/js/jquery-ui-1.8.6.custom.min.js"></script>
    <script type="text/JavaScript" language="JavaScript" src="includes/jquery.layout.min-1.2.0.js"></script>
    <script type="text/javascript" language="JavaScript" src="includes/jsTree.v.1.0rc2/jquery.jstree.js"></script>
    <!--<script type="text/JavaScript" language="JavaScript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>-->
    <script type="text/javascript" src="includes/bauhouse-fluid960gs-7b59717/js/jquery-fluid16.js"></script>
    <script type="text/javascript" src="includes/nicEdit/nicEdit.js"></script>
    <script type="text/javascript" src="includes/nicEdit/plugin/nicExample/nicExample.js"></script>
    <script type="text/javascript" src="includes/rangy-1.0.1/rangy-core.js"></script>
    <script type="text/javascript" src="includes/tipTipv13/jquery.tipTip.minified.js"></script>
    <script type="text/javascript" src="includes/carhartl-jquery-cookie-67fb34f/jquery.cookie.js"></script>
    <script type="text/javascript" src="includes/jquery.countdown.package-1.5.9/jquery.countdown.js"></script>
    

    <script type="text/javascript">

    var myLayout, $westAccordion, $eastAccordion; // init global vars

    function resizeWidgets () {
        //myLayout.resizeAll();
        $westAccordion.accordion("resize");
        $eastAccordion.accordion("resize");
    };
    
    function getFirstRange() {
        var sel = rangy.getSelection();
        return sel.rangeCount ? sel.getRangeAt(0) : null;
    }
    
    function insertNodeAtRange(val) {
        var range = getFirstRange();
        if (range && (range.startContainer.className == undefined || range.startContainer.className.indexOf("nicEdit-main") != -1) ) {
            var el = document.createElement("span");
            //el.style.backgroundColor = "lightblue";
            //el.style.color = "red";
            //el.style.fontWeight = "bold";
            el.appendChild(document.createTextNode(val + " "));
            range.insertNode(el);
            range.setStartAfter(el);
            rangy.getSelection().setSingleRange(range);
        }
    }
    
    function showDialog(link){
   	   $("#divId").dialog("open");
   	   $("#modalIframeId").attr("src",link);
   	   return false;
   	}



    
    function showCorrectAction (actionCode) {
        switch(actionCode)
        {
        case "1":
          $("p.discrepancyNoteAction").show();
          break;
        case "2":
          $("p.emailAction").show();
          break;
        case "3":
          $("p.showAction").show();
          break;
        case "4":
          $("p.hideAction").show();
          break;  
        case "5":
          $("p.insertAction").show();
          break;    
        default:
          alert("Something went wrong, sorry for the inconvenience !! " + actionCode);
        }
    
    
    };

    
    
    function populateItemDetails(oid){
        $.getJSON('tree/itemDetails',{ name: oid }, function(data) {
              $('#itemDetails').html('<p>' + addItemDetailsTable(data) + '</p>');
              $('#accordion2').accordion('activate', 1);
            });
    }
    
    function addItemDetailsTable(data){
        var html = "";
        for(key in data.itemDetailsPerCrfVersion) {
             var codeListItemSize = data.itemDetailsPerCrfVersion[key].codeList.codeListItem.length;
             var responseOptionsValueHtml = " " + "<tr>" + "<th colspan=\"100%\" style=\"border-top: 0px none; border-bottom: 0px none;\"> Response Options/Values (" + codeListItemSize + "):</th>" + "</tr>"
             var dialogResponseOptionsValueHtml = "<table><tbody>" + "<tr>" + "<th colspan=\"100%\" style=\"border-top: 0px none; border-bottom: 0px none;\"> Response Options/Values (" + codeListItemSize + "):</th>" + "</tr>"
             for(key2 in data.itemDetailsPerCrfVersion[key].codeList.codeListItem) {
                 var theVar =
                     "<tr>" 
                     + "<td style=\"padding: 0.2em 3em; border-top: 0px none; border-bottom: 0px none;\">" + data.itemDetailsPerCrfVersion[key].codeList.codeListItem[key2].decode.translatedText[0].value  + "</td>" 
                     + "<td style=\"border-top: 0px none; border-bottom: 0px none;\">" + data.itemDetailsPerCrfVersion[key].codeList.codeListItem[key2].codedValue + "</td>" 
                     + "</tr>";
                 if(key2 < 5){ 
                     responseOptionsValueHtml += theVar;
                     dialogResponseOptionsValueHtml += theVar;
                 }else{
                     dialogResponseOptionsValueHtml += theVar;
                 }                       
             }
             responseOptionsValueHtml += codeListItemSize >= 5 ? "<tr><td colspan=\"100%\" style=\"border-top: 0px none; border-bottom: 0px none;\"><a onclick=\"openLink()\" href=\"#\">more</a></td></tr>" : "";
             $('#dialogResponseOptionsValuesHtml').html(dialogResponseOptionsValueHtml + "</tbody></table>");
             
             html += "<div id=\"tables\" class=\"block\" style=\"margin: 0px;\">"
                 + "<table><tbody>"
                 + "<tr>" 
                 + "<td>" + data.itemName +  "</td>"
                 + "<td>" + data.itemDetailsPerCrfVersion[key].crfVersionName +  "</td>"
                 + "</tr>"
                 + "</tbody></table>"
                 + "<table><tbody>"
                 + "<tr>" + "<th>Item Name:</th>" + "<td>" + data.itemName + "</td>" + "</tr>"
                 + "<tr>" + "<th>CRF Name:</th>" + "<td>" + data.crfName + "</td>" + "</tr>"
                 + "<tr>" + "<th>OID:</th>" + "<td>" + data.oid + "</td>" + "</tr>"
                 + "<tr>" + "<th>Description:</th>" + "<td>" + data.description + "</td>" + "</tr>"
                 + "<tr>" + "<th>Data Type:</th>" + "<td>" + data.dataType + "</td>" + "</tr>"
                 + "<tr>" + "<th>Units:</th>" + "<td>" + data.units + "</td>" + "</tr>"
                 + "<tr>" + "<th>PHI:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].phi + "</td>" + "</tr>"
                 + "<tr>" + "<th>Left Item text:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].leftItemText + "</td>" + "</tr>"
                 + "<tr>" + "<th>Right Item text:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].rightItemText + "</td>" + "</tr>"
                 + "<tr>" + "<th>Default Value:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].defaultValue + "</td>" + "</tr>"
                 + "<tr>" + "<th>Response Layout:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].responseLayout + "</td>" + "</tr>"
                 + "<tr>" + "<th>Response Type:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].responseType + "</td>" + "</tr>"
                 + "<tr>" + "<th>Response Label:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].responseLabel + "</td>" + "</tr>"
                 +  responseOptionsValueHtml
                 + "<tr>" + "<th>Section Label:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].sectionLabel + "</td>" + "</tr>"
                 + "<tr>" + "<th>Group Name:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].groupName + "</td>" + "</tr>"
                 + "<tr>" + "<th>Validation:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].validation + "</td>" + "</tr>"
                 + "<tr>" + "<th>Validation Error Message:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].validationErrorMessage + "</td>" + "</tr>"
                 + "<tr>" + "<th>Required:</th>" + "<td>" + data.itemDetailsPerCrfVersion[key].required + "</td>" + "</tr>"
                 + "</tbody></table>"
                 + "</div>"
                 ;    
            }   
          return html;        
    }
    
    function openLink(){
       $('#dialogResponseOptionsValues').dialog("open");
    }
    

    $(document).ready( function() {
    	
    	
        
        myLayout = $('body').layout({
            west__size:         200
        ,   east__size:         300
        ,   north__size:        46
        ,   south__size:        40
        ,   north__resizable:   false
        ,   north__initClosed:  false
        ,   east__initClosed:   false
        ,   south__initClosed:  false
        ,   spacing_open:       16
        ,   spacing_closed:     16
        ,   north__spacing_closed:  0
        ,   north__spacing_open:  0
            // RESIZE Accordion widget when panes resize
        ,   west__onresize:     function () { $("#accordion1").accordion("resize"); }
        ,   west__onshow_end:     function () { $("#accordion1").accordion("resize"); }
        ,   east__onresize:     function () { $("#accordion2").accordion("resize"); }
        });

        // ACCORDION - in the West pane
        $westAccordion = $("#accordion1").accordion({
            fillSpace:  true
        });
        
        // ACCORDION - in the East pane - in a 'content-div'
        $eastAccordion = $("#accordion2").accordion({
            fillSpace:  true
        });
        
        $('#actions').mouseover(function() {
        	myLayout.allowOverflow(this)
        	$('.dropdownCont').css({'visibility' : 'visible', 'opacity' : '1'})
        });
        
        $('#actions').mouseleave(function() {
            $('.dropdownCont').css({'visibility' : 'hidden', 'opacity' : '0'})
        });
        


        // THEME SWITCHER
        //$('#switcher').themeswitcher();
        // addThemeSwitcher('.ui-layout-north',{ top: '12px', right: '5px' });
        // if a new theme is applied, it could change the height of some content,
        // so call resizeAll to 'correct' any header/footer heights affected
        // NOTE: this is only necessary because we are changing CSS *AFTER LOADING* using themeSwitcher
        setTimeout( resizeWidgets, 1000 ); /* allow time for browser to re-render with new theme */
        
        $("#divId").dialog({
            autoOpen: false,
            modal: true,
            height: 500,
            width: 700
        });
        
        <%-- Initialize Session timeout dialog box, div in center.jsp --%>
        $("#sessionTimeoutDialog").dialog({
        	closeOnEscape: false,
            autoOpen: false,
            modal: true
        }).prev('.ui-dialog-titlebar').find('a').hide();
        
        <%-- Manage session timeout. Open a dialog box 2 minutes before timeout, this runs every 30 mins --%>
        setInterval(function() {
        	var lastAccessedTimeInSession = $.cookie('lastAccessedTime');
        	var currentDate = new Date();
        	//var curentTimeInUTC = currentDate.getTime() + (currentDate.getTimezoneOffset() * 60000);
        	var curentTime = currentDate.getTime();
        	var theTime = parseInt((lastAccessedTimeInSession - curentTime)/60000);
        	console.log(theTime + " " + lastAccessedTimeInSession + " " + curentTime);
        	if ( theTime < 0 && !$("#sessionTimeoutDialog").dialog( "isOpen" ) ){
        		var ocURL = "${sessionScope['scopedTarget.userPreferences'].exitURL}";
        		$('#counter').html("Your session has expired.")
                $('#sessionTimeoutDialog').dialog( "option", "buttons", { "Go back to OpenClinica": function() { window.location = ocURL; } } );
        		$("#sessionTimeoutDialog").dialog("open");
        	}
        	if ( theTime == 2 && !$("#sessionTimeoutDialog").dialog( "isOpen" ) ){
        		var ocURL = "${sessionScope['scopedTarget.userPreferences'].appURL}";
        		$('#counter').countdown('destroy');
                $('#counter').countdown({until: '+0h +2m +00s', format: 'YOWDHMS', significant: 2, layout: '{mnn}{sep}{snn}',
                	onExpiry: function(){
                		//console.log(ocURL)
                		$('#counter').countdown('destroy');
                        $('#counter').html("Your session has expired.")
                        $('#sessionTimeoutDialog').dialog( "option", "buttons", { "Go back to OpenClinica": function() { window.location = ocURL; } } );
                	}
                	});
                $('#sessionTimeoutDialog').dialog( 
                		"option", 
                		"buttons", 
                		{ "Reset": function() {
                			$.getJSON('access/refreshSession');
                			$(this).dialog("close"); 
                			}});
                $("#sessionTimeoutDialog").dialog("open");
        	}
        }, 60000);


        
        
         $("#ruleSetRuleBean").submit(function() {  
            $.post($(this).attr("action"), $(this).serialize(), function(html) {
                //$("#tabs2").replaceWith("<span id=\"tabs2\">" + html + "<\/span>");
                $('#tabs2').html(data);
            });
            return false;  
         });

         $("#dialogResponseOptionsValues").dialog({
             resizable: false,
             height:340,
             autoOpen: false,
             modal: true,
             buttons: {
                 "Close": function() {
                     $( this ).dialog( "close" );
                 }
             }            
         });
         
         $("#duplicateRuleDialog").dialog({
             autoOpen: false,
             modal: true,
             buttons: {
                 "Save": function() {
                	 save();
                     $( this ).dialog( "close" );
                 },
                 "Cancel": function() {
                     $( this ).dialog( "close" );
                 }
             }            
         });
         
         $("#verifyResetDialog").dialog({
             autoOpen: false,
             modal: true,
             buttons: {
                 "Ok": function() {
                	 reset();
                     $( this ).dialog( "close" );
                 },
                 "Cancel": function() {
                     $( this ).dialog( "close" );
                 }
             }            
         });
        
        var $tabs = $("#tabs2").tabs({
            ajaxOptions: {
            	cache: false,
                error: function( xhr, status, index, anchor ) {
                    alert("Couldn't load this tab. We'll try to fix this as soon as possible.");
                }
            }
        });

        $tabs.tabs('option', 'submitFormWhenMovingFromXmlToDesignerTab', true);
        
        $('#tabs2').bind('tabsselect', function (event, ui) {
            //alert(ui.tab + " :: " + ui.panel + " :: " + ui.index + " ::1 ")
            var currentlySelectedTab = $(this).tabs( "option" , "selected");
            if (ui.index == 1) {
            	nicEditors.findEditor('ruleDef.expression.value').saveContent()
                nicEditors.findEditor('target.value').saveContent()
                $.ajax({
                    type: 'POST',
                    url: 'ruleBuilder/processDesignTabSubmit',
                    data: $("#rulesCommand").serialize(),
                    success: function (result) {
                        if (result.isOk == false) alert(result.message);
                    },
                    async: false
                });
            }
            if (currentlySelectedTab == 0 && ui.index == 2 ) {
            	var x = true;
            	nicEditors.findEditor('ruleDef.expression.value').saveContent()
                nicEditors.findEditor('target.value').saveContent()
                $.ajax({
                    type: 'POST',
                    url: 'ruleBuilder/processDesignTabToTest',
                    data: $("#rulesCommand").serialize(),
                    success: function (result) {
                        if (result.length > 0 ){
                        	x=false;
                        	$.post('ruleBuilder/ruleBuilderFormA', $("#rulesCommand").serialize(), function(data) {
                                $('#kkforms').html(data);
                            });
                        }
                    },
                    async: false
                });
                return x; 
            }
            if (currentlySelectedTab == 1 && ui.index == 2 ) {
                var x = true;
                nicEditors.findEditor('ruleDef.expression.value').saveContent()
                nicEditors.findEditor('target.value').saveContent()
                $.ajax({
                    type: 'POST',
                    url: 'ruleBuilder/xmlTabSubmitToTestTab',
                    data: $("#rulesCommandXml").serialize(),
                    success: function (result) {
                        if (result.length > 0 ){
                            x=false;
                            $.post('ruleBuilder/xmlForm', $("#rulesCommandXml").serialize(), function(data) {
                                $('#rulesCommandXmlDiv').html(data);
                            });
                        }
                    },
                    async: false
                });
                return x; 
            }
            if (ui.index == 0 && currentlySelectedTab != 2) {
                var x = true;
                if($(this).tabs( "option" , "submitFormWhenMovingFromXmlToDesignerTab")){
                    $.ajax({
                        type: 'POST',
                        url: 'ruleBuilder/processXmlTabSubmit',
                        data: $("#rulesCommandXml").serialize(),
                        success: function (result) {
                            if (result.length > 0 ){
                                x=false;
                                $("#dialog").dialog("open");
                            }
                        },
                        async: false
                    });
                }
                return x; 
            }
        });
        
        
    });

    </script>

</head>