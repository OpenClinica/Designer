<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="language" content="en" />

    <title>Layout with Accordion</title>

    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/reset.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/text.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/960.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/layout.css" media="screen" />
    <link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/nav.css" media="screen" />
    <!--[if IE 6]><link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/ie6.css" media="screen" /><![endif]-->
    <!--[if IE 7]><link rel="stylesheet" type="text/css" href="includes/bauhouse-fluid960gs-7b59717/css/ie.css" media="screen" /><![endif]-->
    <link rel="stylesheet" type="text/css" href="includes/jsTree.v.1.0rc2/themes/classic/style.css" />
    <link rel="stylesheet" type="text/css" href="includes/layout-default-latest.css" />
    <!--<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery.ui.all.css" />-->
    <link rel="stylesheet" type="text/css" href="includes/jquery-ui-1.8.6.custom/css/custom-theme/jquery-ui-1.8.6.custom.css" /> 
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
    <script type="text/JavaScript" language="JavaScript" src="includes/jquery-1.4.2.min.js"></script>
    <script type="text/JavaScript" language="JavaScript" src="includes/json.min.js"></script>
    <script type="text/JavaScript" language="JavaScript" src="includes/jquery-ui-1.8.6.custom/js/jquery-ui-1.8.6.custom.min.js"></script>
    <script type="text/JavaScript" language="JavaScript" src="includes/jquery.layout.min-1.2.0.js"></script>
    <script type="text/javascript" language="JavaScript" src="includes/jsTree.v.1.0rc2/jquery.jstree.js"></script>
    <!--<script type="text/JavaScript" language="JavaScript" src="http://jqueryui.com/themeroller/themeswitchertool/"></script>-->
    <script type="text/javascript" src="includes/bauhouse-fluid960gs-7b59717/js/jquery-fluid16.js"></script>

    <script type="text/javascript">

    var myLayout, $westAccordion, $eastAccordion; // init global vars

    function resizeWidgets () {
        //myLayout.resizeAll();
        $westAccordion.accordion("resize");
        $eastAccordion.accordion("resize");
    };
    
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
            west__size:         300
        ,   east__size:         200
        ,   north__size:        50
        ,   north__resizable:   false
        ,   south__size:        40
        ,   north__initClosed:  false
        ,   east__initClosed:   false
        ,   south__initClosed:  false
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


        // THEME SWITCHER
        //$('#switcher').themeswitcher();
        // addThemeSwitcher('.ui-layout-north',{ top: '12px', right: '5px' });
        // if a new theme is applied, it could change the height of some content,
        // so call resizeAll to 'correct' any header/footer heights affected
        // NOTE: this is only necessary because we are changing CSS *AFTER LOADING* using themeSwitcher
        setTimeout( resizeWidgets, 1000 ); /* allow time for browser to re-render with new theme */

        
        
         $("#ruleSetRuleBean").submit(function() {  
            $.post($(this).attr("action"), $(this).serialize(), function(html) {
                //$("#tabs2").replaceWith("<span id=\"tabs2\">" + html + "<\/span>");
                $('#tabs2').html(data);
                $('html, body').animate({ scrollTop: $("#message").offset().top }, 500);
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
        
        var $tabs = $("#tabs2").tabs();
        $tabs.tabs('option', 'submitFormWhenMovingFromXmlToDesignerTab', true);
        
        $('#tabs2').bind('tabsselect', function (event, ui) {
            //alert(ui.tab + " :: " + ui.panel + " :: " + ui.index + " ::1 ")
            var currentlySelectedTab = $(this).tabs( "option" , "selected");
            if (ui.index == 1 || ui.index == 2 ) {
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
            if (ui.index == 0 && currentlySelectedTab != 2) {
                var x = true;
                if($(this).tabs( "option" , "submitFormWhenMovingFromXmlToDesignerTab")){
                	$.ajax({
                        type: 'POST',
                        url: 'ruleBuilder/processXmlTabSubmit',
                        data: $("#rulesCommandXml").serialize(),
                        success: function (result) {
                        	if (result.messages.length > 0 ){
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
<body>
<div id="dialogResponseOptionsValues" title="Response Options/ Response Values">
  <p id="dialogResponseOptionsValuesHtml">...</p>
</div>
<div class="ui-layout-north ui-widget-content" style="display: none;">
    <div style="float: right; margin-right: 160px;">
        <!--
        <button onClick="resizeWidgets()">Resize</button> &nbsp;
        <button onClick="removeUITheme(); resizeWidgets()">Remove Theme</button>
        -->
        <h1>Rule Designer</h1>
    </div>
    <div id="switcher"></div>
    <img src="includes/OpenClinica_Enterprise_xxsmall.gif">
</div>

<div class="ui-layout-south ui-widget-content " style="display: none;">
    &copy; 2004-2009 Akaza Research LLC and collaborators.
    <span style="float: right;">
    <img border="0" title="Developed by Akaza Research" alt="Developed by Akaza Research" src="includes/OpenClinica_footer_xxsmall.gif"></a>
    <span>
</div>

<div class="ui-layout-center" style="display: none;"> 
    <!--<h3 class="ui-widget-header">Center Pane</h3>-->
    <!--<p class="ui-layout-content ui-widget-content">-->
        <div  class="ui-layout-content ui-widget-content" id="theCenter">
            <!--  <span id="tabs2"></span> USE THIS IF NO TABS INVOLVED-->
            <div id="tabs2">
                <ul>
                    <li><a href="<c:url value="/ruleBuilder/ruleBuilderFormA" />">Designer</a></li>
                    <li><a href="<c:url value="/ruleBuilder/xmlForm" />">XML</a></li>
                    <li><a href="<c:url value="/ruleBuilder/testForm" />">Test</a></li>
                </ul>
            </div> 
        </div>
    <!--</p>-->
</div>

<div class="ui-layout-west" style="display: none;">
<h3 class="ui-widget-content ui-state-hover ui-widget">&nbsp; <fmt:message key="label_show_oid"></fmt:message> 
<input type="radio" id="toggleTreeViewBetweenOidOrName" name="toggleTreeViewBetweenOidOrName" value="oid">
<fmt:message key="label_or_names"/>
<input type="radio" id="toggleTreeViewBetweenOidOrName" name="toggleTreeViewBetweenOidOrName" checked value="name">
</h3>

    <div id="accordion1" class="basic">

<h3>
                <a href="#"><fmt:message key="label_view_by_crf"/> </a>
                <!--<input type="button" style="clear: both;" id="toggle_node" value="toggle_node" class="button"> -->
             </h3>
            <div style="padding-left:6px; padding-top:0px">
                    
<div id="demo3" class="demo">
</div>
<script type="text/javascript" class="source">
$(function () {
    $('input[name=toggleTreeViewBetweenOidOrName]').change(function () {
        $("#demo4").jstree("refresh", "-1");
        $("#demo3").jstree("refresh", "-1");
        $("#demo2").jstree("refresh", "-1");
    });
    $("#demo3").jstree({
         "dnd": {
            "drop_target": "#ocTextArea, input[name*='lazyProperties']",
            "drop_finish": function (data) {
                var ctrl = data.r[0],
                    pos = 0,
                    sel;
                if (document.selection) {
                    //ctrl.focus();
                    //sel = document.selection.createRange();
                    //sel.moveStart('character', -ctrl.value.length);
                    //pos = sel.text.length;
                    ctrl.focus();

                    var r = document.selection.createRange();
                    if (r == null) {
                        return 0;
                    }

                    var re = ctrl.createTextRange(),
                        rc = re.duplicate();
                    re.moveToBookmark(r.getBookmark());
                    rc.setEndPoint('EndToStart', re);

                    pos = rc.text.length;
                } else if (ctrl.selectionStart || ctrl.selectionStart == '0') {
                    pos = ctrl.selectionStart;
                }
                ctrl.value = ctrl.value.slice(0, pos) + " " + data.o.attr("oid") + " " + ctrl.value.slice(pos);
            },
            "drag_check": function (data) {
                if (data.r.attr("id") == "phtml_1") {
                    return false;
                }
                return {
                    after: false,
                    before: false,
                    inside: false
                };
            },
            "drag_finish": function () {
                alert("DRAG OK");
            }
        },
        "themes": {
            "theme": "classic",
            "dots": true,
            "icons": true
        },
        "json_data": {
            "ajax": {
                "url": function (node) {
                    if ($('input[name=toggleTreeViewBetweenOidOrName]:checked').val() == 'oid') {
                        if (node == "-1") return 'tree/crfListNewOid';
                        if (node.attr("ocType") == "crf") return 'tree/crfBasedGroupListOid';
                        if (node.attr("ocType") == "itemGroup") return 'tree/crfBasedItemListOid';
                    } else {
                        if (node == "-1") return 'tree/crfListNew';
                        if (node.attr("ocType") == "crf") return 'tree/crfBasedGroupList';
                        if (node.attr("ocType") == "itemGroup") return 'tree/crfBasedItemList';
                    }
                    // etc
                },
                "data": function (n) {
                    if (n == "-1") return {
                        name: '0'
                    };
                    if (n.attr("ocType") == "crf") return {
                        crfOid: n.attr("oid")
                    };
                    if (n.attr("ocType") == "itemGroup") return {
                        itemGroupOid: n.attr("oid"),
                        crfOid: n.attr("crfOid")
                    };
                    // return { name: n.attr ? n.attr("oid") : '0' }; 
                    //return { name: 'krikor' }; 
                }
            }
        },
        "plugins": ["themes", "json_data", "dnd"]
    });
});
</script>

            </div>


            <h3><a href="#"><fmt:message key="label_view_by_crf_version"/> </a></h3>
            <div style="padding-left:6px; padding-top:0px">
                        
                    
<div id="demo2" class="demo"></div>
<script type="text/javascript" class="source">
$(function () {
    $("#demo2").jstree({
        "dnd": {
            "drop_target": "#ocTextArea, input[name*='lazyProperties']",
            "drop_finish": function (data) {
                var ctrl = data.r[0],
                    pos = 0,
                    sel;
                if (document.selection) {
                    //ctrl.focus();
                    //sel = document.selection.createRange();
                    //sel.moveStart('character', -ctrl.value.length);
                    //pos = sel.text.length;
                    ctrl.focus();

                    var r = document.selection.createRange();
                    if (r == null) {
                        return 0;
                    }

                    var re = ctrl.createTextRange(),
                        rc = re.duplicate();
                    re.moveToBookmark(r.getBookmark());
                    rc.setEndPoint('EndToStart', re);

                    pos = rc.text.length;
                } else if (ctrl.selectionStart || ctrl.selectionStart == '0') {
                    pos = ctrl.selectionStart;
                }
                if (data.r.attr("name")=="target.value") {
                    ctrl.value = ctrl.value.slice(0, pos) + "" + data.o.attr("targetOid") + " " + ctrl.value.slice(pos);
                } else {
                    ctrl.value = ctrl.value.slice(0, pos) + "" + data.o.attr("oid") + " " + ctrl.value.slice(pos);
                }
            },
            "drag_check": function (data) {
                if (data.r.attr("id") == "phtml_1") {
                    return false;
                }
                return {
                    after: false,
                    before: false,
                    inside: false
                };
            },
            "drag_finish": function () {
                alert("DRAG OK");
            }
        },
        "themes": {
            "theme": "classic",
            "dots": true,
            "icons": true
        },
        "json_data": {
            "ajax": {
                "url": function (node) {
                    if ($('input[name=toggleTreeViewBetweenOidOrName]:checked').val() == 'oid') {
                        if (node == "-1") return 'tree/crfListOid';
                        if (node.attr("ocType") == "crf") return 'tree/crfVersionListOid';
                        if (node.attr("ocType") == "crfVersion") return 'tree/groupListOid';
                        if (node.attr("ocType") == "itemGroup") return 'tree/itemListOid';
                    } else {
                        if (node == "-1") return 'tree/crfList';
                        if (node.attr("ocType") == "crf") return 'tree/crfVersionList';
                        if (node.attr("ocType") == "crfVersion") return 'tree/groupList';
                        if (node.attr("ocType") == "itemGroup") return 'tree/itemList';
                    }
                },
                "data": function (n) {
                    if (n == "-1") return {
                        name: '0'
                    };
                    if (n.attr("ocType") == "crf") return {
                        crfOid: n.attr("oid")
                    };
                    if (n.attr("ocType") == "crfVersion") return {
                        crfVersionOid: n.attr("oid"),
                        crfOid: n.attr("crfOid")
                    };
                    if (n.attr("ocType") == "itemGroup") return {
                        itemGroupOid: n.attr("oid"),
                        crfVersionOid: n.attr("crfVersionOid"),
                        crfOid: n.attr("crfOid")
                    };
                }
            }
        },
        "plugins": ["themes", "json_data", "dnd"]
    });
});
</script>

            </div>

             
            <h3><a href="#"><fmt:message key="label_view_by_event"/> </a></h3>
            <div style="padding-left:6px; padding-top:0px">
                <div id="demo4" class="demo"></div>
<script type="text/javascript" class="source">
$(function () {
    $("#demo4").jstree({
        "dnd": {
           "drop_target": "#ocTextArea, input[name*='lazyProperties']",
           "drop_finish": function (data) {
               alert("test");
               var ctrl = data.r[0],
                   pos = 0,
                   sel;
               if (document.selection) {
                   //ctrl.focus();
                   //sel = document.selection.createRange();
                   //sel.moveStart('character', -ctrl.value.length);
                   //pos = sel.text.length;
                   ctrl.focus();

                   var r = document.selection.createRange();
                   if (r == null) {
                       return 0;
                   }

                   var re = ctrl.createTextRange(),
                       rc = re.duplicate();
                   re.moveToBookmark(r.getBookmark());
                   rc.setEndPoint('EndToStart', re);

                   pos = rc.text.length;
               } else if (ctrl.selectionStart || ctrl.selectionStart == '0') {
                   pos = ctrl.selectionStart;
               }
               ctrl.value = ctrl.value.slice(0, pos) + " " + data.o.attr("oid") + " " + ctrl.value.slice(pos);
           },
           "drag_check": function (data) {
               if (data.r.attr("id") == "phtml_1") {
                   return false;
               }
               return {
                   after: false,
                   before: false,
                   inside: false
               };
           },
           "drag_finish": function () {
               alert("DRAG OK");
           }
       },
        "themes": {
            "theme": "classic",
            "dots": true,
            "icons": true
        },
        "json_data": {
            "ajax": {
                "url": function (node) {
                    if ($('input[name=toggleTreeViewBetweenOidOrName]:checked').val() == 'oid') {
                        if (node == "-1") return 'tree/eventsListOid';
                        if (node.attr("ocType") == "event") return 'tree/eventCrfsListOid';
                        if (node.attr("ocType") == "crf") return 'tree/eventCrfGroupsAndItemsListOid';
                        if (node.attr("ocType") == "itemGroup") return 'tree/eventCrfGroupItemsListOid';
                    } else {
                        if (node == "-1") return 'tree/eventsList';
                        if (node.attr("ocType") == "event") return 'tree/eventCrfsList';
                        if (node.attr("ocType") == "crf") return 'tree/eventCrfGroupsAndItemsList';
                        if (node.attr("ocType") == "itemGroup") return 'tree/eventCrfGroupItemsList';
                    }
                },
                "data": function (n) {
                    if (n == "-1") return {
                        name: '0'
                    };
                    if (n.attr("ocType") == "event") return {
                        eventOid: n.attr("oid")
                    };
                    if (n.attr("ocType") == "crf") return {
                        crfOid: n.attr("oid"),
                        eventOid: n.attr("eventOid")
                    };
                    if (n.attr("ocType") == "itemGroup") return {
                        itemGroupOid: n.attr("oid"),
                        crfOid: n.attr("crfOid"),
                        eventOid: n.attr("eventOid")
                    };
                }
            }
        },
        "plugins": ["themes", "json_data"]
    });
});
</script>
            </div>

            <h3><a href="#">Section 4</a></h3>

            <div>
                <p>Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames 
                    ac turpis egestas.</p>
                <p>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;
                    Aenean lacinia mauris vel est.</p>
                <p>Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus.
                    Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>
            </div>

    </div>
</div>

<div class="ui-layout-east" style="display: none;">
    <h3 class="ui-widget-header">Help</h3>

    <div class="ui-layout-content">
        <div id="accordion2" class="basic">

            <h3><a href="#">Getting started</a></h3>
            <div>
                <p>1. Expand the tree on the left and drag a selected Item into the Target.</p>

                <p>2. Provide an OID for your Rule and write an expression.You can build your expression by 
                   dragging items from the tree on the left.</p>
                <p>3. Select an action from the drop down.Fill the selected action.</p>   
                <p>4. Submit your rule.</p>   
            </div>
            
            <h3><a href="#">Item Metadata</a></h3>
            <div>
                <p id="itemDetails">Select an item to view its data.</p>

            </div>
            <!--
            <h3><a href="#">Section 3</a></h3>
            <div>
                <p>Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis.
                    Phasellus pellentesque purus in massa. Aenean in pede.</p>
                <p>Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, 
                    magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.</p>

                <ul>
                    <li>List item one</li>
                    <li>List item two</li>
                    <li>List item three</li>
                </ul>
            </div>

            <h3><a href="#">Section 4</a></h3>

            <div>
                <p>Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames 
                    ac turpis egestas.</p>
                <p>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae;
                    Aenean lacinia mauris vel est.</p>
                <p>Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus.
                    Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.</p>
            </div>
            -->

        </div>

    </div>

    <!--<h4 class="ui-widget-content ui-state-hover">Accordion inside DIV.ui-layout-content</h4>-->
</div>

</body>