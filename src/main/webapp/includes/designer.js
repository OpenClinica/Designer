    function addRun(propPrefix,idPrefix,runOnAdmin,runOnInitial,runOnDouble,runOnBatch){
        var runOnAdminVal = runOnAdmin == true ? "checked" : "";
        var runOnInitialVal = runOnInitial == true ? "checked" : "";
        var runOnDoubleVal = runOnDouble == true ? "checked" : "";
        var runOnBatchVal = runOnBatch == true ? "checked" : "";
        var html = "<div id=\"tables\" class=\"block\" style=\"margin: 0px;\">"
            + "<table><tbody>"
            + "<tr>"
            + "<td colspan=\"100%\">Specify the mode where this action should run</td>"
            + "</tr>"
            + "<tr>"
            + "<th>Admin data entry</th>"
            + "<th>Initial data entry</th>"
            + "<th>Double data entry</th>"
            + "<th>Batch</th>"
            + "</tr>"
            + "<tr>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnAdminVal +  " name=\"" + propPrefix + ".run.administrativeDataEntry\" id=\"" + idPrefix + ".run.administrativeDataEntry1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".run.administrativeDataEntry\">"
            + "</td>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnInitialVal + " name=\"" + propPrefix + ".run.initialDataEntry\" id=\"" + idPrefix + ".run.initialDataEntry1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".run.initialDataEntry\">"
            + "</td>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnDoubleVal +  " name=\"" + propPrefix + ".run.doubleDataEntry\" id=\"" + idPrefix + ".run.doubleDataEntry1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".run.doubleDataEntry\">"
            + "</td>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnBatchVal +  " name=\"" + propPrefix + ".run.batch\" id=\"" + idPrefix + ".run.batch1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".run.batch\">"
            + "</td>"
            + "</tr>"
            + "</tbody></table>"
            + "</div>"
            ;    
        return html;        
    }
    
    
    function addDnAction(toolExpressionEvaluatesTo,tooltipMessage){
            var nameAttrOLlazyDiscrepancyNoteActions =  $("textarea[name*=lazyDiscrepancyNoteActions]").last().attr("id");
            index = nameAttrOLlazyDiscrepancyNoteActions == null ? 0 : parseInt(nameAttrOLlazyDiscrepancyNoteActions.match(/[\d]/)) +1;
            var propPrefix = "ruleRef.lazyDiscrepancyNoteActions[" + index  + "]";
            var idPrefix = "ruleRef.lazyDiscrepancyNoteActions" + index  + "";
            $("#ruleActions").append(
             "<span id=\"lazyDiscrepancyNoteActions" + index + "\">"
             + "<fieldset class=\"login\">"
             + "<legend><span>Discrepancy Note Action " //+ index
             + "<a id=\"removeDnAction\" href=\"#\" onclick=\"$('#lazyDiscrepancyNoteActions"+ index +"').remove();\"><img title=\"Remove\" alt=\"Remove\" src=\"images/bt_Remove.gif\" name=\"bt_Remove1\"><\/a>"
             + "<\/span><\/legend>"
             + "<p>"
             + "<label id=\"ruleRef.lazyDiscrepancyNoteActions" + index  + ".ifExpressionEvaluatesLabel\" for=\"ruleRef.lazyDiscrepancyNoteActions[" + index  + "].ifExpressionEvaluates\">Evaluates to:<\/label>"
             + " <select name=\"ruleRef.lazyDiscrepancyNoteActions[" + index  + "].ifExpressionEvaluates\" id=\"ruleRef.lazyDiscrepancyNoteActions[" + index  + "].ifExpressionEvaluates\" title=\"" + toolExpressionEvaluatesTo + "\" > "
             + " <option value=\"true\">true<\/option> "
             + " <option value=\"false\">false<\/option> "
             + "  <\/select>"
             + " <\/p><p>"
             + "<label id=\"ruleRef.lazyDiscrepancyNoteActions" + index  + ".messageLabel\" for=\"ruleRef.lazyDiscrepancyNoteActions[" + index  + "].message\">Message:<\/label>"
             + "<textarea cols=\"50\" rows=\"3\" name=\"ruleRef.lazyDiscrepancyNoteActions[" + index  + "].message\" id=\"ruleRef.lazyDiscrepancyNoteActions" + index  + ".message\" title=\"" + tooltipMessage + "\" ><\/textarea>"
             + "<\/p>"
             + addRun(propPrefix,idPrefix,true,true,true,true)
             + "<\/fieldset>"
             + "<\/span>"
             );
             $('#theCenter').animate({ scrollTop: $("#lazyDiscrepancyNoteActions" + index).offset().top }, 1000);
             initializeToolTips();
    }
    
    function addEmailAction(toolExpressionEvaluatesTo,tooltipMessage,tooltipTo){
            var nameAttrOLlazyDiscrepancyNoteActions =  $("textarea[name*=lazyEmailActions]").last().attr("id");
            index = nameAttrOLlazyDiscrepancyNoteActions == null ? 0 : parseInt(nameAttrOLlazyDiscrepancyNoteActions.match(/[\d]/)) +1;
            var propPrefix = "ruleRef.lazyEmailActions[" + index  + "]";
            var idPrefix = "ruleRef.lazyEmailActions" + index  + "";
            $("#ruleActions").append(
             "<span id=\"lazyEmailActions" + index + "\">"
             + "<fieldset class=\"login\">"
             + "<legend><span>Email Action " //+ index
             + "<a id=\"removeDnAction\" href=\"#\" onclick=\"$('#lazyEmailActions"+ index +"').remove();\"><img title=\"Remove\" alt=\"Remove\" src=\"images/bt_Remove.gif\" name=\"bt_Remove1\"><\/a>"
             + "<\/span><\/legend>"
             + "<p>"
             + "<label id=\"ruleRef.lazyEmailActions" + index  + ".ifExpressionEvaluatesLabel\" for=\"ruleRef.lazyEmailActions[" + index  + "].ifExpressionEvaluates\">Evaluates to:<\/label>"
             + " <select name=\"ruleRef.lazyEmailActions[" + index  + "].ifExpressionEvaluates\" id=\"ruleRef.lazyEmailActions" + index  + ".ifExpressionEvaluates\" title=\"" + toolExpressionEvaluatesTo + "\" > "
             + " <option value=\"true\">true<\/option> "
             + " <option value=\"false\">false<\/option> "
             + "  <\/select>"
             + " <\/p><p>"
             + "<label id=\"ruleRef.lazyEmailActions" + index  + ".toLabel\" for=\"ruleRef.lazyEmailActions[" + index  + "].message\">To:<\/label>"
             + "<input type=\"text\" value=\"\" name=\"ruleRef.lazyEmailActions[" + index  + "].to\" id=\"ruleRef.lazyEmailActions" + index  + ".to\" title=\"" + tooltipTo + "\" > "
             + "<\/p><p>"
             + "<label id=\"ruleRef.lazyEmailActions" + index  + ".messageLabel\" for=\"ruleRef.lazyEmailActions[" + index  + "].message\">Message:<\/label>"
             + "<textarea cols=\"50\" rows=\"3\" name=\"ruleRef.lazyEmailActions[" + index  + "].message\" id=\"ruleRef.lazyEmailActions" + index  + ".message\" title=\"" + tooltipMessage + "\" ><\/textarea>"
             + "<\/p>"
             + addRun(propPrefix,idPrefix,true,true,true,true)
             + "<\/fieldset>"
             + "<\/span>"
             );
             $('#theCenter').animate({ scrollTop: $("#lazyEmailActions" + index).offset().top }, 1000);
             initializeToolTips();
    }
    
    function addShowAction(toolExpressionEvaluatesTo,tooltipMessage,tooltipApplyOnShow){
            var nameAttrOLlazyShowActions =  $("textarea[name*=lazyShowActions]").last().attr("id");
            index = nameAttrOLlazyShowActions == null ? 0 : parseInt(nameAttrOLlazyShowActions.match(/[\d]/)) +1;
            var propPrefix = "ruleRef.lazyShowActions[" + index  + "]";
            var idPrefix = "ruleRef.lazyShowActions" + index  + "";
            $("#ruleActions").append(
             "<span id=\"lazyShowActions" + index + "\">"
             + "<fieldset class=\"login\">"
             + "<legend><span>Show Action " //+ index
             + "<a id=\"removeDnAction\" href=\"#\" onclick=\"$('#lazyShowActions"+ index +"').remove();\"><img title=\"Remove\" alt=\"Remove\" src=\"images/bt_Remove.gif\" name=\"bt_Remove1\"><\/a>"
             + "<\/span><\/legend>"
             + "<p>"
             + "<label id=\"ruleRef.lazyShowActions" + index  + ".ifExpressionEvaluatesLabel\" for=\"ruleRef.lazyShowActions[" + index  + "].ifExpressionEvaluates\">Evaluates to:<\/label>"
             + " <select name=\"ruleRef.lazyShowActions[" + index  + "].ifExpressionEvaluates\" id=\"ruleRef.lazyShowActions[" + index  + "].ifExpressionEvaluates\" title=\"" + toolExpressionEvaluatesTo + "\" > "
             + " <option value=\"true\">true<\/option> "
             + " <option value=\"false\">false<\/option> "
             + "  <\/select>"
             + " <\/p><p>"
             + "<label id=\"ruleRef.lazyShowActions" + index  + ".messageLabel\" for=\"ruleRef.lazyShowActions[" + index  + "].message\">Message:<\/label>"
             + "<textarea cols=\"50\" rows=\"3\" name=\"ruleRef.lazyShowActions[" + index  + "].message\" id=\"ruleRef.lazyShowActions" + index  + ".message\" title=\"" + tooltipMessage + "\" ><\/textarea>"
             + " <\/p><p>"
             + " <label>Apply on:<\/label> "
             + " <a href=\"#\" onClick=\"addProperty('ruleRef.lazyShowActions" + index + "','ruleRef.lazyShowActions[" + index + "]','" + tooltipApplyOnShow + "')\"><img border=\"0\" src=\"images/create_new.gif\"><\/a>"
             + "<\/p>"
             + "<span id=\"ruleRef.lazyShowActions" + index + ".propertiesContainer\"><\/span>"
             + addRun(propPrefix,idPrefix,true,true,true,false)
             + "<\/fieldset>"
             + "<\/span>"
             );
             $('#theCenter').animate({ scrollTop: $("#lazyShowActions" + index).offset().top }, 1000);
             initializeToolTips();
             
    }
    
    
    function addHideAction(toolExpressionEvaluatesTo,tooltipMessage,tooltipApplyOnHide){
            var nameAttrOLlazyHideActions =  $("textarea[name*=lazyHideActions]").last().attr("id");
            index = nameAttrOLlazyHideActions == null ? 0 : parseInt(nameAttrOLlazyHideActions.match(/[\d]/)) +1;
            var propPrefix = "ruleRef.lazyHideActions[" + index  + "]";
            var idPrefix = "ruleRef.lazyHideActions" + index  + "";
            $("#ruleActions").append(
             "<span id=\"lazyHideActions" + index + "\">"
             + "<fieldset class=\"login\">"
             + "<legend><span>Hide Action " //+ index
             + "<a id=\"removeDnAction\" href=\"#\" onclick=\"$('#lazyHideActions"+ index +"').remove();\"><img title=\"Remove\" alt=\"Remove\" src=\"images/bt_Remove.gif\" name=\"bt_Remove1\"><\/a>"
             + "<\/span><\/legend>"
             + "<p>"
             + "<label id=\"ruleRef.lazyHideActions" + index  + ".ifExpressionEvaluatesLabel\" for=\"ruleRef.lazyHideActions[" + index  + "].ifExpressionEvaluates\">Evaluates to:<\/label>"
             + " <select name=\"ruleRef.lazyHideActions[" + index  + "].ifExpressionEvaluates\" id=\"ruleRef.lazyHideActions[" + index  + "].ifExpressionEvaluates\" title=\"" + toolExpressionEvaluatesTo + "\" > "
             + " <option value=\"true\">true<\/option> "
             + " <option value=\"false\">false<\/option> "
             + "  <\/select>"
             + " <\/p><p>"
             + "<label id=\"ruleRef.lazyHideActions" + index  + ".messageLabel\" for=\"ruleRef.lazyHideActions[" + index  + "].message\">Message:<\/label>"
             + "<textarea cols=\"50\" rows=\"3\" name=\"ruleRef.lazyHideActions[" + index  + "].message\" id=\"ruleRef.lazyHideActions" + index  + ".message\" title=\"" + tooltipMessage + "\" ><\/textarea>"
             + " <\/p><p>"
             + " <label>Apply on:<\/label> "
             + " <a href=\"#\" onClick=\"addProperty('ruleRef.lazyHideActions" + index + "','ruleRef.lazyHideActions[" + index + "]','" + tooltipApplyOnHide + "')\"><img border=\"0\" src=\"images/create_new.gif\"><\/a>"
             + "<\/p>"
             + "<span id=\"ruleRef.lazyHideActions" + index + ".propertiesContainer\"><\/span>"
             + addRun(propPrefix,idPrefix,true,true,true,false)
             + "<\/fieldset>"
             + "<\/span>"
             );
             $('#theCenter').animate({ scrollTop: $("#lazyHideActions" + index).offset().top }, 1000);
             initializeToolTips();
    }
    
    function addInsertAction(toolExpressionEvaluatesTo,tooltipApplyOnInsert,tooltipApplyOnValue,tooltipApplyOnExpression){
            var nameAttrOLlazyInsertActions =  $("select[name*=lazyInsertActions]").last().attr("id");
            index = nameAttrOLlazyInsertActions == null ? 0 : parseInt(nameAttrOLlazyInsertActions.match(/[\d]/)) +1;
            var propPrefix = "ruleRef.lazyInsertActions[" + index  + "]";
            var idPrefix = "ruleRef.lazyInsertActions" + index  + "";
            $("#ruleActions").append(
             "<span id=\"lazyInsertActions" + index + "\">"
             + "<fieldset class=\"login\">"
             + "<legend><span>Insert Action " //+ index
             + "<a id=\"removeDnAction\" href=\"#\" onclick=\"$('#lazyInsertActions"+ index +"').remove();\"><img title=\"Remove\" alt=\"Remove\" src=\"images/bt_Remove.gif\" name=\"bt_Remove1\"><\/a>"
             + "<\/span><\/legend>"
             + "<p>"
             + "<label id=\"ruleRef.lazyInsertActions" + index  + ".ifExpressionEvaluatesLabel\" for=\"ruleRef.lazyInsertActions[" + index  + "].ifExpressionEvaluates\">Evaluates to:<\/label>"
             + " <select name=\"ruleRef.lazyInsertActions[" + index  + "].ifExpressionEvaluates\" id=\"ruleRef.lazyInsertActions[" + index  + "].ifExpressionEvaluates\" title=\"" + toolExpressionEvaluatesTo + "\" > "
             + " <option value=\"true\">true<\/option> "
             + " <option value=\"false\">false<\/option> "
             + "  <\/select>"
             //+ " <\/p><p>"
             //+ "<label id=\"ruleRef.lazyInsertActions" + index  + ".messageLabel\" for=\"ruleRef.lazyInsertActions[" + index  + "].message\">Message:<\/label>"
             //+ "<textarea cols=\"50\" rows=\"3\" name=\"ruleRef.lazyInsertActions[" + index  + "].message\" id=\"ruleRef.lazyInsertActions" + index  + ".message\"><\/textarea>"
             + " <\/p><p>"
             + " <label>Apply on:<\/label> "
             + " <a href=\"#\" onClick=\"addProperty('ruleRef.lazyInsertActions" + index + "','ruleRef.lazyInsertActions[" + index + "]','" + tooltipApplyOnInsert + "','" + tooltipApplyOnValue + "','" + tooltipApplyOnExpression + "')\"><img border=\"0\" src=\"images/create_new.gif\"><\/a>"
             + "<\/p>"
             + "<span id=\"ruleRef.lazyInsertActions" + index + ".propertiesContainer\"><\/span>"
             + addRun(propPrefix,idPrefix,true,true,true,false)
             + "<\/fieldset>"
             + "<\/span>"
             );
             $('#theCenter').animate({ scrollTop: $("#lazyInsertActions" + index).offset().top }, 1000);
             initializeToolTips();
    }
    
    
    function addProperty(actionType,nameAttr,message,insertActionValue,insertActionExpression){
        // action type can be lazy????Actions0.lazyProperties
        var nameAttrWithProp = nameAttr + ".lazyProperties";
        var nameAttrOflazyProperties =  $('input[name*="'+ nameAttrWithProp +'"]').last().attr("id");
        var index = nameAttrOflazyProperties == null ? 0 : parseInt(nameAttrOflazyProperties.match(/\d(?=.placeHolder|.OID|.value|.valueExpression.value)/)) +1;        
        var propId = actionType + ".lazyProperties" + index;
        var propName = nameAttr + ".lazyProperties[" + index + "]";
        var insertActionProperties = actionType.indexOf("InsertAction") == -1 ? "" : addInsertProperty(propName,propId,insertActionValue,insertActionExpression)
        
        $("#" + actionType.replace(".","\\.") + "\\.propertiesContainer").append(
         "<span id=\"" + propId + "\">"
         +"<p>"
         + "<label>&nbsp;<\/label>"
         //+ "<label id=\"" + propId + ".oid" + "\" for=\"" + propName + ".oid" +  "\">Oid:<\/label>"
         //+ "<input type=\"text\" value=\"Drag an Item or type an OID\" onfocus=\"if (this.value == 'Drag an Item or type an OID') this.value = ''\" onblur=\"if (this.value == '') this.value = 'Drag an Item or type an OID'\" name=\"" + propName + ".OID" +  "\" id=\"" + propId + ".OID\"" + "class=\"jstree-drop\" title=\"Yes this is a select\"" + "\">"
         + "<input type=\"text\" name=\"" + propName + ".OID" +  "\" id=\"" + propId + ".OID\"" + "class=\"jstree-drop\" title=\" " + message  + "\"" + "\">"
         + insertActionProperties
         + "<a id=\"removePropertyAction\" href=\"#\" onclick=\"$('#"+ propId.replace(/\./g,'\\\\.') +"').remove();\"><img title=\"Remove\" alt=\"Remove\" src=\"images/bt_Remove.gif\" name=\"bt_Remove1\"><\/a>"
         + "<input type=\"hidden\" name=\"" + propName + ".placeHolder" +  "\" id=\"" + propId + ".placeHolder\"" + " value=\"placeHolder\"" + "\">"
         //+ "<input type=\"text\" value=\"\" name=\"" + propName + ".value" +  "\" id=\"" + propId + ".value\"" + "\">"
         + "<\/p>"      
         + "<\/span>"      
        );
        initializeToolTips();
    }
    
    function addInsertProperty(propName,propId,insertActionValue,insertActionExpression){
         var k = 
         //"<p>"
         //+ "<label>&nbsp;<\/label>"
         "Value: <input type=\"text\" value=\"\" name=\"" + propName + ".value" +  "\"  title=\"" + insertActionValue + "\"  id=\"" + propId + ".value" + "\">"
         + "Or Expression: <input type=\"text\" value=\"\" name=\"" + propName + ".valueExpression.value" +  "\" title=\"" + insertActionExpression + "\"   id=\"" + propId + ".valueExpression.value" + "\">"
         //+ "<\/p>";
         return k;
    }

    function addEventAction(toolExpressionEvaluatesTo,tooltipExpression,tooltipApplyTo,tooltipEventProperty){
        var nameAttrOLlazyEventActions =  $("select[name*=lazyEventActions]").last().attr("id");
        index = nameAttrOLlazyEventActions == null ? 0 : parseInt(nameAttrOLlazyEventActions.match(/[\d]/)) +1;
        var propPrefix = "ruleRef.lazyEventActions[" + index  + "]";
        var idPrefix = "ruleRef.lazyEventActions" + index  + "";
        $("#ruleActions").append(
         "<span id=\"lazyEventActions" + index + "\">"
         + "<fieldset class=\"login\">"
         + "<legend><span>Event Action " //+ index
         + "<a id=\"removeEventAction\" href=\"#\" onclick=\"$('#lazyEventActions"+ index +"').remove();\"><img title=\"Remove\" alt=\"Remove\" src=\"images/bt_Remove.gif\" name=\"bt_Remove1\"><\/a>"
         + "<\/span><\/legend>"
         + "<p>"
         + "<label id=\"ruleRef.lazyEventActions" + index  + ".ifExpressionEvaluatesLabel\" for=\"ruleRef.lazyEventActions[" + index  + "].ifExpressionEvaluates\">Evaluates to:<\/label>"
         + " <select name=\"ruleRef.lazyEventActions[" + index  + "].ifExpressionEvaluates\" id=\"ruleRef.lazyEventActions" + index  + ".ifExpressionEvaluates\" title=\"" + toolExpressionEvaluatesTo + "\" > "
         + " <option value=\"true\">true<\/option> "
         + " <option value=\"false\">false<\/option> "
         + "  <\/select>"
         + " <\/p><p>"
         + "<label id=\"ruleRef.lazyEventActions" + index  + ".applyToLabel\" for=\"ruleRef.lazyEventActions[" + index  + "].OID\">Apply To:<\/label>"
         + "<input type=\"text\" value=\"\" name=\"ruleRef.lazyEventActions[" + index  + "].OID\" id=\"ruleRef.lazyEventActions" + index  + ".OID\" title=\"" + tooltipApplyTo + "\" style=\"width: 50%\"> "
         + "<\/p><p>"
         + "<label id=\"ruleRef.lazyEventActions" + index  + ".lazyProperties" + index  + ".PropertyLabel\" for=\"ruleRef.lazyEventActions[" + index  + "].lazyProperties[" + index  + "].Property\">Property:<\/label>"
         + " <select name=\"ruleRef.lazyEventActions[" + index  + "].lazyProperties[" + index  + "].Property\" id=\"ruleRef.lazyEventActions" + index  + ".lazyProperties" + index  + ".Property\" title=\"" + tooltipEventProperty + "\" > "
         + " <option value=\"\">Select<\/option> "
         + " <option value=\"STARTDATE\">STARTDATE<\/option> "
         + "  <\/select>"
         + " <\/p><p>"
         + "<label id=\"ruleRef.lazyEventActions" + index  + ".lazyProperties" + index  + ".valueExpression.valueLabel\" for=\"ruleRef.lazyEventActions[" + index  + "].lazyProperties[" + index  + "].valueExpression.value\">Value Expression:<\/label>"
         + "<input type=\"text\" value=\"\" name=\"ruleRef.lazyEventActions[" + index  + "].lazyProperties[" + index  + "].valueExpression.value\" id=\"ruleRef.lazyEventActions" + index  + ".lazyProperties" + index  + ".valueExpression.value\" title=\"" + tooltipExpression + "\" style=\"width: 50%\">"
         + "<input type=\"hidden\" name=\"ruleRef.lazyEventActions[" + index  + "].lazyProperties[" + index  + "].placeHolder" +  "\" id=\"ruleRef.lazyEventActions" + index  + ".lazyProperties" + index  + ".placeHolder\"" + " value=\"placeHolder\"" + "\">"
         + "<\/p>"
         + addRunOnStatus(propPrefix,idPrefix,true,true,false,false,false,false)
         + "<\/fieldset>"
         + "<\/span>"
         );
         $('#theCenter').animate({ scrollTop: $("#lazyEventActions" + index).offset().top }, 1000);
         initializeToolTips();
    }

    function addRunOnStatus(propPrefix,idPrefix,runOnNotStarted,runOnScheduled,runOnStarted,runOnCompleted,runOnSkipped,runOnStopped){
        var runOnNotStartedVal = runOnNotStarted == true ? "checked" : "";
        var runOnScheduledVal = runOnScheduled == true ? "checked" : "";
        var runOnStartedVal = runOnStarted == true ? "checked" : "";
        var runOnCompletedVal = runOnCompleted == true ? "checked" : "";
        var runOnSkippedVal = runOnSkipped == true ? "checked" : "";
        var runOnStoppedVal = runOnStopped == true ? "checked" : "";
        var html = "<div id=\"tables\" class=\"block\" style=\"margin: 0px;\">"
            + "<table><tbody>"
            + "<tr>"
            + "<td colspan=\"100%\">Run this action if the status of the 'Apply To' Event is:</td>"
            + "</tr>"
            + "<tr>"
            + "<th>Not Scheduled</th>"
            + "<th>Scheduled</th>"
            + "<th>Data Entry Started</th>"
            + "<th>Completed</th>"
            + "<th>Skipped</th>"
            + "<th>Stopped</th>"
            + "</tr>"
            + "<tr>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnNotStartedVal +  " name=\"" + propPrefix + ".runOnStatus.notScheduled\" id=\"" + idPrefix + ".runOnStatus.notScheduled1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".runOnStatus.notScheduled\">"
            + "</td>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnScheduledVal + " name=\"" + propPrefix + ".runOnStatus.scheduled\" id=\"" + idPrefix + ".runOnStatus.scheduled1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".runOnStatus.scheduled\">"
            + "</td>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnStartedVal +  " name=\"" + propPrefix + ".runOnStatus.dataEntryStarted\" id=\"" + idPrefix + ".runOnStatus.dataEntryStarted1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".runOnStatus.dataEntryStarted\">"
            + "</td>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnCompletedVal +  " name=\"" + propPrefix + ".runOnStatus.completed\" id=\"" + idPrefix + ".runOnStatus.completed1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".runOnStatus.completed\">"
            + "</td>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnSkippedVal +  " name=\"" + propPrefix + ".runOnStatus.skipped\" id=\"" + idPrefix + ".runOnStatus.skipped1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".runOnStatus.skipped\">"
            + "</td>"
            + "<td>"
            + "<input type=\"checkbox\" " + runOnStoppedVal +  " name=\"" + propPrefix + ".runOnStatus.stopped\" id=\"" + idPrefix + ".runOnStatus.stopped1\">" 
            + "<input type=\"hidden\" value=\"on\" name=\"_" + propPrefix + ".runOnStatus.stopped\">"
            + "</td>"
            + "</tr>"
            + "</tbody></table>"
            + "</div>"
            ;    
        return html;        
    }

    function initializeWYSIWYGToolTips(targetText,ruleExpressionText){
        $("#targetWYSIWYG>div").tipTip({maxWidth: "auto", edgeOffset: 10,defaultPosition: "right", content:targetText });
        $("#ruleExpressionWYSIWYG>div").tipTip({maxWidth: "auto", edgeOffset: 10,defaultPosition: "right", content: ruleExpressionText });
    }
    
    
    function initializeToolTips() {
        
        $("input").tipTip({maxWidth: "auto", edgeOffset: 10,defaultPosition: "right"});
        $("select").tipTip({maxWidth: "auto", edgeOffset: 10,defaultPosition: "right"});
        $("textarea").tipTip({maxWidth: "auto", edgeOffset: 10,defaultPosition: "right"});
        $("a").tipTip({maxWidth: "auto", edgeOffset: 10,defaultPosition: "right"});
    }