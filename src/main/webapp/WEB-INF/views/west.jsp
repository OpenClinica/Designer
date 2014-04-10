<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="ui-layout-west" style="display: none;">
<h3 class="ui-widget-content ui-state-hover ui-widget">&nbsp; <fmt:message key="label_show_oid"></fmt:message> 
<input type="radio" id="toggleTreeViewBetweenOidOrName" name="toggleTreeViewBetweenOidOrName" value="oid">
<fmt:message key="label_or_names"/>
<input type="radio" id="toggleTreeViewBetweenOidOrName" name="toggleTreeViewBetweenOidOrName" checked value="name">
</h3>
    <div class="ui-layout-content">
        <div id="accordion1" class="basic">
            <h3>
                <a href="#"><fmt:message key="label_view_by_crf"/> </a>
             </h3>
                      <div style="padding-left:6px; padding-top:0px">                    
<div id="demo3" class="demo"></div>
<script type="text/javascript" class="source">

$(function () {
    $('input[name=toggleTreeViewBetweenOidOrName]').change(function () {
        $("#demo4").jstree("refresh", "-1");
        $("#demo3").jstree("refresh", "-1");
        $("#demo2").jstree("refresh", "-1");
    });
    $("#demo3").jstree({
         "dnd": {
            "drop_target": "#ocTextArea, input[name*='lazyProperties'], #targetWYSIWYG>div:last, #ruleExpressionWYSIWYG>div:last",
            "drop_finish": function (data) {
                //data.r.html(data.o.attr("oid"));
                
                if ( data.r.context.nodeName == 'DIV'){
                    //data.r.attr("class","nicEdit-main nicEdit-selected")
                    $(data.r.context).focus();
                    insertNodeAtRange(data.o.attr("oid"));
                }else{
                    var ctrl = data.r[0],
                    pos = 0,
                    sel;
                    if (document.selection) {
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
                ctrl.value = ctrl.value.slice(0, pos) + "" + data.o.attr("oid") + "" + ctrl.value.slice(pos);
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
            "theme": "designer",
            "dots": true,
            "icons": true
        },
        "crrm" : { move : { check_move : function() { return false; } } },
        "json_data": {
            "ajax": {
                "error" : function(x,e,o){
                    alert("replace this with a dialog communicating that your session has timedout"); 
                },
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
        "plugins": ["themes", "json_data", "dnd","crrm"]
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
            "drop_target": "#ocTextArea, input[name*='lazyProperties'], #targetWYSIWYG>div:last, #ruleExpressionWYSIWYG>div:last",
            "drop_finish": function (data) {
                //data.r.html(data.o.attr("oid"));
                if ( data.r.context.nodeName == 'DIV'){
                    wysiwygDropTargetId = new String($(data.r.context).parent().parent().attr("id"));
                    oid="";
                    if (wysiwygDropTargetId == "targetWYSIWYG" || wysiwygDropTargetId == "ruleExpressionWYSIWYG"){
                        oid = data.o.attr("targetOid") == null ? data.o.attr("oid") : data.o.attr("targetOid");
                    }else
                        oid = data.o.attr("oid");
                    $(data.r.context).focus();
                    insertNodeAtRange(oid);
                }else{
                    var ctrl = data.r[0],
                    pos = 0,
                    sel;
                    if (document.selection) {
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
                ctrl.value = ctrl.value.slice(0, pos) + "" + data.o.attr("oid") + "" + ctrl.value.slice(pos);
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
            "theme": "designer",
            "dots": true,
            "icons": true
        },
        "crrm" : { move : { check_move : function() { return false; } } },
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
        "plugins": ["themes", "json_data", "dnd","crrm"]
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
            "drop_target": "#ocTextArea, input[name*='lazyProperties'], #targetWYSIWYG>div:last, #ruleExpressionWYSIWYG>div:last",
            "drop_finish": function (data) {
                //data.r.html(data.o.attr("oid"));
                if ( data.r.context.nodeName == 'DIV'){
                    wysiwygDropTargetId = new String($(data.r.context).parent().parent().attr("id"));
                    oid="";
                    if (wysiwygDropTargetId == "targetWYSIWYG" || wysiwygDropTargetId == "ruleExpressionWYSIWYG"){
                        oid = data.o.attr("targetOid") == null ? data.o.attr("oid") : data.o.attr("targetOid");
                    }else
                        oid = data.o.attr("oid");
                    $(data.r.context).focus();
                    insertNodeAtRange(oid);
                }else{
                    var ctrl = data.r[0],
                    pos = 0,
                    sel;
                    if (document.selection) {
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
                ctrl.value = ctrl.value.slice(0, pos) + "" + data.o.attr("oid") + "" + ctrl.value.slice(pos);
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
            "theme": "designer",
            "dots": true,
            "icons": true
        },
        "crrm" : { move : { check_move : function() { return false; } } },
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
        "plugins": ["themes", "json_data", "dnd", "crrm"]
    });
});
</script>
            </div>
        
        </div>

    </div>
</div>

<script type="text/javascript">
$(function () {
	$(".jstree-leaf a").live("click", function(e) {   
		populateItemDetails($(this).attr("oid"));
	})  
	
});
</script>
	
	
	
}
