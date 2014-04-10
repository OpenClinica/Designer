/**
* nicExample
* @description: Designer plugins for nicEdit
* @requires: nicCore, nicPane, nicAdvancedButton
* @author: Krikor Krumlian
*/
/* START CONFIG */
var nicExampleOptions = {
  buttons : {
	  'example' : {name : __('Equal'), type : 'nicEditorExampleButton'}
  },iconFiles : {'example' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Equals.png'}
};
/* END CONFIG */
var nicEditorExampleButton = nicEditorButton.extend({   
	mouseClick : function() {
	  insertNodeAtRange('eq')
	}
});
nicEditors.registerPlugin(nicPlugin,nicExampleOptions);


var nicNotEqualOptions = {
  buttons : {
	  'notEqual' : {name : __('Not&nbsp;equal'), type : 'nicNotEqualButton'}
  },iconFiles : {'notEqual' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_NotEqual.png'}
};
/* END CONFIG */
var nicNotEqualButton = nicEditorButton.extend({   
	mouseClick : function() {
	  insertNodeAtRange('ne')
	}
});
nicEditors.registerPlugin(nicPlugin,nicNotEqualOptions);

var nicGreaterOptions = {
  buttons : {
	  'greater' : {name : __('Greater than'), type : 'nicGreaterButton'}
  },iconFiles : {'greater' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Greater.png'}
};
/* END CONFIG */
var nicGreaterButton = nicEditorButton.extend({   
	mouseClick : function() {
	  insertNodeAtRange('gt')
	}
});
nicEditors.registerPlugin(nicPlugin,nicGreaterOptions);

var nicGreaterOrEqualOptions = {
  buttons : {
	  'greaterOrEqual' : {name : __('Greater or equal to'), type : 'nicGreaterOrEqualButton'}
  },iconFiles : {'greaterOrEqual' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_GreaterEqual.png'}
};
/* END CONFIG */
var nicGreaterOrEqualButton = nicEditorButton.extend({   
	mouseClick : function() {
	  insertNodeAtRange('gte')
	}
});
nicEditors.registerPlugin(nicPlugin,nicGreaterOrEqualOptions);

var nicLessOptions = {
  buttons : {
	  'less' : {name : __('Less than'), type : 'nicLessButton'}
  },iconFiles : {'less' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Less.png'}
};
/* END CONFIG */
var nicLessButton = nicEditorButton.extend({   
	mouseClick : function() {
	  insertNodeAtRange('lt')
	}
});
nicEditors.registerPlugin(nicPlugin,nicLessOptions);

var nicLessOrEqualOptions = {
  buttons : {
	  'lessOrEqual' : {name : __('Less or equal to'), type : 'nicLessOrEqualButton'}
  },iconFiles : {'lessOrEqual' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_LessEqual.png'}
};
/* END CONFIG */
var nicLessOrEqualButton = nicEditorButton.extend({   
	mouseClick : function() {
	  insertNodeAtRange('lte')
	}
});
nicEditors.registerPlugin(nicPlugin,nicLessOrEqualOptions);

var nicAndOptions = {
  buttons : {
	  'and' : {name : __('And'), type : 'nicAndButton'}
  },iconFiles : {'and' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_And.png'}
};
/* END CONFIG */
var nicAndButton = nicEditorButton.extend({   
	mouseClick : function() {
	  insertNodeAtRange('and')
	}
});
nicEditors.registerPlugin(nicPlugin,nicAndOptions);

var nicOrOptions = {
  buttons : {
	  'or' : {name : __('Or'), type : 'nicOrButton'}
  },iconFiles : {'or' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Or.png'}
};
/* END CONFIG */
var nicOrButton = nicEditorButton.extend({   
	mouseClick : function() {
		insertNodeAtRange('or')
	}
});
nicEditors.registerPlugin(nicPlugin,nicOrOptions);

var nicPlusOptions = {
		  buttons : {
			  'plus' : {name : __('Plus'), type : 'nicPlusButton'}
		  },iconFiles : {'plus' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Plus.png'}
		};
		/* END CONFIG */
		var nicPlusButton = nicEditorButton.extend({   
			mouseClick : function() {
				insertNodeAtRange('+')
			}
		});
nicEditors.registerPlugin(nicPlugin,nicPlusOptions);

var nicMinusOptions = {
		  buttons : {
			  'minus' : {name : __('Minus'), type : 'nicMinusButton'}
		  },iconFiles : {'minus' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Minus.png'}
		};
		/* END CONFIG */
		var nicMinusButton = nicEditorButton.extend({   
			mouseClick : function() {
				insertNodeAtRange('-')
			}
		});
nicEditors.registerPlugin(nicPlugin,nicMinusOptions);

var nicMultiplyOptions = {
		  buttons : {
			  'multiply' : {name : __('Multiply'), type : 'nicMultiplyButton'}
		  },iconFiles : {'multiply' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Multiply.png'}
		};
		/* END CONFIG */
		var nicMultiplyButton = nicEditorButton.extend({   
			mouseClick : function() {
				insertNodeAtRange('*')
			}
		});
nicEditors.registerPlugin(nicPlugin,nicMultiplyOptions);

var nicDivideOptions = {
		  buttons : {
			  'divide' : {name : __('Divide'), type : 'nicDivideButton'}
		  },iconFiles : {'divide' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Divide.png'}
		};
		/* END CONFIG */
		var nicDivideButton = nicEditorButton.extend({   
			mouseClick : function() {
				insertNodeAtRange('/')
			}
		});
nicEditors.registerPlugin(nicPlugin,nicDivideOptions);

var nicParenthesesOptions = {
		  buttons : {
			  'parentheses' : {name : __('Parentheses'), type : 'nicParenthesesButton'}
		  },iconFiles : {'parentheses' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Parentheses.png'}
		};
		/* END CONFIG */
		var nicParenthesesButton = nicEditorButton.extend({   
			mouseClick : function() {
				insertNodeAtRange('(' + this.ne.selectedInstance.getSel() + ')');
			}
		});
nicEditors.registerPlugin(nicPlugin,nicParenthesesOptions);


var nicItemDetailOptions = {
		buttons : {
			  'itemDetail' : {name : __('Highlight OID and press this button'), type : 'nicItemDetailButton'}
		  },iconFiles : {'itemDetail' : 'includes/nicEdit/plugin/nicExample/icons/icon_expression_Metadata.png'}
		};
		/* END CONFIG */
		var nicItemDetailButton = nicEditorButton.extend({   
			mouseClick : function() {
				
				$('#highlightedTextInWYSIWYG').val(this.ne.selectedInstance.getSelDesigner())
		        $("#highlightedTextInWYSIWYG").focus();
			}
		});
nicEditors.registerPlugin(nicPlugin,nicItemDetailOptions);