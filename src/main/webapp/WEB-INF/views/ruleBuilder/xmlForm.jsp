<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="designerTags" tagdir="/WEB-INF/tags/designer" %>

<div id="rulesCommandXmlDiv">
<style>
#navAccount{position:relative}
#navAccount ul{background:#fff;border:1px solid #333;border-bottom:2px solid #2d4486;display:none;margin-right:-1px;margin-top:-1px;min-width:300px;padding:10px 0 5px;position:absolute;right:0;_right:-1px;top:100%;*width:100%;_width:200px;z-index:1}
#navAccount.openToggler ul,
.no_js #navAccount:hover ul{display:block}
#navAccount li{display:block;float:none}

.xmlTextArea
{
    border:1px solid #999999;
    width:100%;
}


</style>

<div class="box">
<!--<h2><a href="#" id="toggle-forms">Forms</a></h2>-->
<div class="block" id="forms">

<form:form modelAttribute="rulesCommand" id="rulesCommandXml" action="ruleBuilder/xmlForm" method="post">

    <span class="topButtons">
    <button id="xmlValidateButton1" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Validate</span></button>
    <button id="xmlSaveButtonTop1" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Save</span></button>
    </span>
    
    <designerTags:renderPageMessages/>
    <fieldset class="login noBorder login">
    <!--<legend>Login Information</legend>-->
    <p>
        <!--<form:label id="xmlLabel" for="xml" path="xml" cssErrorClass="error">Xml: </form:label>--> 
        <form:textarea path="xml" id="ocTextArea" class="xmlTextArea" rows="40%"/>
        <form:errors path="xml" cssClass="error" />
    </p>
    </fieldset>
    
</form:form>
<div class="buttonRight">
    <button id="xmlValidateButton2" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Validate</span></button>
    <button id="xmlSaveButtonTop2" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Save</span></button>
</div>
</div>

<div id="dialog" title="Basic dialog">
  <p>There was an error submitting the provided XML.</p>
</div>
</div>
</p>



<script type="text/javascript">

function save(){
	$('form#rulesCommandXml').attr({action: "ruleBuilder/xmlForm?ignoreDuplicates=true"});
    $('#rulesCommandXml').submit();
}

function reset(){
	$.get("ruleBuilder/xmlForm?reset=true", function(data) {
        $('#rulesCommandXmlDiv').html(data);
    });
}
  
    $(document).ready( function() {
        
        $("#rulesCommandXml").submit(function() {
            $.post($(this).attr("action"), $(this).serialize(), function(data) {
            	$('#rulesCommandXmlDiv').html(data);
            });
            return false;  
        });
        
        $("#xmlValidateButton1,#xmlValidateButton2").click(function(){
            $('form#rulesCommandXml').attr({action: "ruleBuilder/xmlFormValidate"});
            $('#rulesCommandXml').submit();
            return false;  
        });
        
        $("#xmlSaveButtonTop1,#xmlSaveButtonTop2").click(function(){
        	$('form#rulesCommandXml').attr({action: "ruleBuilder/xmlForm?ignoreDuplicates=false"});
            $('#rulesCommandXml').submit();
            return false;  
        });
        
        
        
        
        $("#dialog").dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                "Discard changes and go back to Designer tab": function() {
                	var $tabs = $('#tabs2').tabs();
                	$tabs.tabs('option', 'submitFormWhenMovingFromXmlToDesignerTab', false);
                	$tabs.tabs('select', 0);
                	$tabs.tabs('option', 'submitFormWhenMovingFromXmlToDesignerTab', true);
                    $( this ).dialog( "close" );
                },
                "Stay and keep editing": function() {
                    $( this ).dialog( "close" );
                }
            }

            
        });

    });
</script>

</div>