<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>jQuery QueryBuilder Example</title>
    <link rel="stylesheet" href="${ctx}/js/jqbuilder/css/bootstrap.min.css" id="bt-theme">
    <link rel="stylesheet" href="${ctx}/js/jqbuilder/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="${ctx}/js/jqbuilder/css/awesome-bootstrap-checkbox.css">
    <link rel="stylesheet" href="${ctx}/js/jqbuilder/css/bootstrap-slider.min.css">
    <link rel="stylesheet" href="${ctx}/js/jqbuilder/css/selectize.bootstrap3.css">
    <link rel="stylesheet" href="${ctx}/js/jqbuilder/css/query-builder.default.css" id="qb-theme">  
  
    <%-- <script src="${ctx}/js/jqbuilder/js/jquery.js"></script> --%>
    <script type="text/javascript" src="${ctx}/js/jquery-2.1.4/jquery-2.1.4.js"></script>
	<%-- <script src="${ctx}/js/jqbuilder/js/bootstrap.min.js"></script> --%>
	<script type="text/javascript" src="${ctx}/js/bootstrap-3.3.5/js/bootstrap.min.js"></script>
	<script src="${ctx}/js/jqbuilder/js/bootstrap-select.min.js"  charset="utf-8"></script>
	<script src="${ctx}/js/jqbuilder/js/bootbox.js"></script>
	<script src="${ctx}/js/jqbuilder/js/bootstrap-slider.min.js"></script>
	<script src="${ctx}/js/jqbuilder/js/selectize.min.js"></script>
	<script src="${ctx}/js/jqbuilder/js/jQuery.extendext.min.js"></script>
	<script src="${ctx}/js/jqbuilder/js/sql-parser.js"></script>
	<script src="${ctx}/js/jqbuilder/js/doT.js"></script>	
	<script src="${ctx}/js/jqbuilder/js/query-builder.js" charset="utf-8"></script>
  
  
  <style>
    .flag { display: inline-block; }
  </style>
</head>

<div class="container">
  <div class="col-md-12 col-lg-10 col-lg-offset-1">
    <div class="page-header">
      <a class="pull-right" href="https://github.com/mistic100/jQuery-QueryBuilder">
        <img src="https://assets.github.com/images/icons/emoji/octocat.png" width=48px height=48px>
      </a>
      <h1>jQuery QueryBuilder <small>Example</small></h1>
    </div>

    <div class="alert alert-info alert-dismissible" role="alert">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      You must execute <code>bower install</code> in the example directory to run this demo.
    </div>

    <div class="well well-sm">
      <label>Theme:</label>
      <div class="btn-group">
        <button class="btn btn-primary btn-sm change-theme" data-qb="../dist/css/query-builder.default.min.css" data-bt="bower_components/bootstrap/dist/css/bootstrap.min.css">Default</button>
        <button class="btn btn-primary btn-sm change-theme" data-qb="../dist/css/query-builder.dark.min.css" data-bt="bower_components/bootswatch-dist/css/bootstrap.min.css">Dark</button>
      </div>
      
      <label>Language:</label>
      <select id="language1" name="language" class="selectpicker show-tick show-menu-arrow" data-width="auto" data-live-search="true">
        <option value="sq" data-icon="flag flag-al">Albanian</option>
        <option value="de" data-icon="flag flag-de">German</option>
        <option value="da" data-icon="flag flag-dk">Danish</option>
        <option value="en" data-icon="flag flag-gb" selected>English</option>
        <option value="es" data-icon="flag flag-es">Spanish</option>
        <option value="fr" data-icon="flag flag-fr">French</option>
        <option value="it" data-icon="flag flag-it">Italian</option>
        <option value="fa-IR" data-icon="flag flag-ir">Farsi</option>
        <option value="nl" data-icon="flag flag-nl">Dutch</option>
        <option value="no" data-icon="flag flag-no">Norwegian</option>
        <option value="pl" data-icon="flag flag-pl">Polish</option>
        <option value="pt-PT" data-icon="flag flag-pt-PT">Portuguese</option>
        <option value="pt-BR" data-icon="flag flag-pt-BR">Brazilian Portuguese</option>
        <option value="ro" data-icon="flag flag-ro">Romanian</option>
        <option value="ru" data-icon="flag flag-ru">Russian</option>
      </select>
      <select id="language2" class="selectpicker show-tick show-menu-arrow" data-width="auto" data-live-search="true" title="请选择">
        <option value="sq" >Albanian</option>
        <option value="de" >German</option>
        <option value="da" >Danish</option>
        <option value="en">English</option>
        <option value="es" >Spanish</option>
      </select>
      <button type="button" class="btn btn-primary" onclick="search()" id="searchbtn"> Search</button>
    </div>

    <div id="builder"></div>

    <div class="btn-group">
      <button class="btn btn-warning reset">Reset</button>
      <button class="btn btn-success set">Set rules</button>
      <button class="btn btn-success set-mongo">Set rules from MongoDB</button>
      <button class="btn btn-success set-sql">Set rules from SQL</button>
      <button class="btn btn-warning set-filters">Set filters</button>
    </div>

    <div class="btn-group">
      <button class="btn btn-default" disabled>Get:</button>
      <button class="btn btn-primary parse-json">JSON</button>
      <button class="btn btn-primary parse-sql" data-stmt="false">SQL</button>
      <button class="btn btn-primary parse-sql" data-stmt="question_mark">SQL statement</button>
      <button class="btn btn-primary parse-mongo">MongoDB</button>      
    </div>
    
   	<div class="alert"></div>
    <div id="searchResult" class="btn-group">
    	<button type="button" class="btn btn-xs btn-primary tag" onclick="this.remove()" value="test1"> test1&times</button>
    	<button type="button" class="btn btn-xs btn-primary tag" onclick="btnRemove(this)" value="test2"> test2<i class="glyphicon glyphicon-remove"></i></button>
    	<button type="button" class="btn btn-xs btn-primary tag" value="test3"> test3<i class="glyphicon glyphicon-remove"></i></button>
    </div>
    <button type="button" class="btn btn-xs btn-danger" onclick="show()"> result</button>
    
    <div id="result" class="hide">
      <h3>Output</h3>
      <pre></pre>
    </div>
  </div>
</div>



<script>
function search(){
	$('#searchbtn').prop('disabled',true);
	var l1=$('#language1').val();
	var l2=$('#language2').val();
	var v=l1+":"+l2;
	$('#searchResult').append('<button type="button" class="btn btn-xs btn-primary tag" onclick="btnRemove(this)" value="'+v+'"> '+v+'<i class="glyphicon glyphicon-remove"></i></button>');
	//submit
}
$('#language1').on('change', function() {
  //alert($('#language1').val());
  //$('#language2').val('en');//改值但不刷新
  //$('#language2').selectpicker('render');//强制刷新
 	//$('#language2').selectpicker('val', 'de');//改值并刷新
 	$('#language2').find("option").remove();
 	$('#language2').append("<option value='new' >new</option>");
 	$('#language2').selectpicker('refresh');
});
function btnRemove(btn){
	btn.remove();	
	//submit
	$('#searchbtn').removeAttr('disabled');
}	
$('.tag').on('click', function() {
  this.remove();
});

function show(){
	var i=$('.tag').size();
	//alert($('.tag')[1].value);
	var v="";
	for(j=0;j<i;j++){
		v+=$('.tag')[j].value+";";
	}
		alert(v);
}
	
var $b = $('#builder');

var options = {
  allow_empty: false,
  display_errors:false,	//	不显示错误提示
  default_condition:'OR',
  select_placeholder:'-请选择-',
  
  default_filter: 'name',
  
  optgroups: {
    core: {
      en: 'Core',
      fr: 'Coeur'
    }
  },
  	
  plugins: {
    'bt-tooltip-errors': { delay: 100},
    'sortable': null,
    'filter-description': { mode: 'bootbox' },
    'bt-selectpicker':  {liveSearch:true, size:20},
    'unique-filter': null,
    'bt-checkbox': { color: 'primary' },
    'invert': null,
  },

  filters: [
  /*
   * basic
   */
  {
    id: 'name',
    label: {
      en: '名字',
      fr: 'Nom'
    },
    type: 'string',
    optgroup: '中文组',
    default_value: 'Mistic',
    size: 30,
    unique: true
  },
  /*
   * textarea
   */
  {
    id: 'bson',
    label: '名称',
    type: 'string',
    input: 'textarea',
    operators: ['equal'],
    size: 30,
    rows: 3
  },
  /*
   * checkbox
   */
  {
    id: 'category',
    label: 'Category',
    type: 'integer',
    input: 'checkbox',
    optgroup: 'core',
    vertical:true,//垂直显示
    values: {
      1: 'Books',
      2: 'Movies',
      3: 'Music',
      4: 'Tools',
      5: 'Goodies',
      6: 'Clothes'
    },
    colors: {
      1: 'foo',
      2: 'warning',
      5: 'success'
    },
    operators: ['yes_in', 'not_in', 'equal', 'not_equal', 'is_null', 'is_not_null']
  },
  /*
   * select
   */
  {
    id: 'continent',
    label: 'Continent',
    type: 'string',
    input: 'select',
//    multiple:true,
    optgroup: 'core',
//    plugin:'selectpicker',
    placeholder: 'Select something',
    values: {
      'eur': 'Europe',
      'asia': 'Asia',
      'oce': 'Oceania',
      'afr': 'Africa',
      'na': 'North America',
      'sa': 'South America'
    },
    operators: ['equal', 'not_equal', 'is_null', 'is_not_null']
  },
  /*
  *selectpicker
  */
  {
  	id: 'selelct',
  	label: 'Select',
  	type: 'string',
  	input: 'select',
  	plugin: 'selectpicker',
    multiple: true,
//  	placeholder: '--请选择--',
  	plugin_config: {liveSearch: true,size:5},
  	values:{
  		'1': '科幻',
  		'2': '剧情',
  		'3': '犯罪',
  		'4': '西部',
  		'5': '科学'
  	}
  },
  
  /*
   * Selectize
   */
  {
    id: 'state',
    label: 'State',
    type: 'string',
    input: 'select',
    multiple: true,
    plugin: 'selectize',
    plugin_config: {
      valueField: 'id',
      labelField: 'name',
      searchField: 'name',
      sortField: 'name',
      options: [
        { id: "AL", name: "Alabama" },
        { id: "AK", name: "Alaska" },
        { id: "AZ", name: "Arizona" },
        { id: "AR", name: "Arkansas" },
        { id: "CA", name: "California" },
        { id: "CO", name: "Colorado" },
        { id: "CT", name: "Connecticut" },
        { id: "DE", name: "Delaware" },
        { id: "DC", name: "District of Columbia" },
        { id: "FL", name: "Florida" },
        { id: "GA", name: "Georgia" },
        { id: "HI", name: "Hawaii" },
        { id: "ID", name: "Idaho" }
      ]
    },
    valueSetter: function(rule, value) {
      rule.$el.find('.rule-value-container select')[0].selectize.setValue(value);
    }
  },
  /*
   * radio
   
  {
    id: 'in_stock',
    label: 'In stock',
    type: 'integer',
    input: 'radio',
    optgroup: 'plugin',
    values: {
      1: 'Yes',
      0: 'No'
    },
    operators: ['equal']
  },*/
  /*
   * double
   */
  {
    id: 'price',
    label: 'Price',
    type: 'double',
    size: 5,
    validation: {
      min: 0,
      step: 0.01
    },
    data: {
      class: 'com.example.PriceTag'
    }
  },
  /*
   * slider
   */
  {
    id: 'rate',
    label: 'Rate',
    type: 'integer',
    validation: {
      min: 0,
      max: 100
    },
    plugin: 'slider',
    plugin_config: {
      min: 0,
      max: 100,
      value: 0
    },
    onAfterSetValue: function(rule, value) {
      var input = rule.$el.find('.rule-value-container input');
      input.slider('setValue', value);
      input.val(value); // don't know why I need it
    }
  },
  /*
   * placeholder and regex validation
   */
  {
    id: 'id',
    label: 'Identifier',
    type: 'string',
    optgroup: 'plugin',
    placeholder: '____-____-____',
    size: 14,
    operators: ['equal', 'not_equal'],
    validation: {
      format: /^.{4}-.{4}-.{4}$/
    }
  },
  /*
   * custom input
   */
  {
    id: 'coord',
    label: 'Coordinates',
    type: 'string',
    default_value: 'C.5',
    description: 'The letter is the cadran identifier:\
<ul>\
  <li><b>A</b>: alpha</li>\
  <li><b>B</b>: beta</li>\
  <li><b>C</b>: gamma</li>\
</ul>',
    validation: {
      format: /^[A-C]{1}.[1-6]{1}$/
    },
    input: function(rule) {
      var $container = rule.$el.find('.rule-value-container');

      $container.on('change', '[name=coord_1]', function(){
        var h = '';

        switch ($(this).val()) {
          case 'A':
            h = '<option value="-1">-</option> <option value="1">1</option> <option value="2">2</option>';
            break;
          case 'B':
            h = '<option value="-1">-</option> <option value="3">3</option> <option value="4">4</option>';
            break;
          case 'C':
            h = '<option value="-1">-</option> <option value="5">5</option> <option value="6">6</option>';
            break;
        }

        $container.find('[name=coord_2]').html(h).toggle(h!='');
      });

      return '\
      <select name="coord_1" class="form-control"> \
        <option value="-1">-</option> \
        <option value="A">A</option> \
        <option value="B">B</option> \
        <option value="C">C</option> \
      </select> \
      <select name="coord_2" class="form-control" style="display:none;"></select>';
    },
    valueParser: function(rule, value) {
      return rule.$el.find('[name=coord_1]').val()
        +'.'+rule.$el.find('[name=coord_2]').val();
    },
    valueSetter: function(rule, value) {
      if (rule.operator.nb_inputs !== 0) {
        var val = value.split('.');
        rule.$el.find('[name=coord_1]').val(val[0]).trigger('change');
        rule.$el.find('[name=coord_2]').val(val[1]);
      }
    }
  }]
};

// init
$('#builder').queryBuilder(options);

$('#builder').on('afterCreateRuleInput.queryBuilder', function(e, rule) {
    if (rule.filter.plugin == 'selectize') {
        rule.$el.find('.rule-value-container').css('min-width', '200px')
          .find('.selectize-control').removeClass('form-control');
    }
});

// change language
$('[name=language]').selectpicker().on('change', function() {
  var lang = $(this).val();
  
  var done = function() {
    var rules = $b.queryBuilder('getRules');
    if (!$.isEmptyObject(rules)) {
      options.rules = rules;
    }
    options.lang_code = lang;
    $b.queryBuilder('destroy');
    $('#builder').queryBuilder(options);
  };
  
  if ($.fn.queryBuilder.regional[lang] === undefined) {
    $.getScript('../dist/i18n/query-builder.' + lang + '.js', done);
  }
  else {
    done();
  }
});

// change theme
$('.change-theme').on('click', function() {
    $('#qb-theme').replaceWith('<link rel="stylesheet" href="' + $(this).data('qb') + '" id="qb-theme">');
    $('#bt-theme').replaceWith('<link rel="stylesheet" href="' + $(this).data('bt') + '" id="bt-theme">');
});

// set rules
$('.set').on('click', function() {
  $('#builder').queryBuilder('setRules', {
    condition: 'AND',
    rules: [{
      id: 'price',
      operator: 'between',
      value: [10.25, 15.52],
      flags: {
        no_delete: true,
        filter_readonly: true
      },
      data: {
        unit: '€'
      }
    }, {
      id: 'state',
      operator: 'equal',
      value: 'AK',
    }, {
      condition: 'OR',
      rules: [{
        id: 'category',
        operator: 'equal',
        value: 2
      }, {
        id: 'coord',
        operator: 'equal',
        value: 'B.3'
      }]
    }]
  });
});

// set rules from MongoDB
$('.set-mongo').on('click', function() {
  $('#builder').queryBuilder('setRulesFromMongo', {
    "$and": [{
      "name": {
        "$regex": "^(?!Mistic)"
      }
    }, {
      "price": { "$gte": 0, "$lte": 100 }
    }, {
      "$or": [{
        "category": 2
      }, {
        "category": { "$in": [4, 5] }
      }]
    }]
  });
});

// set rules from SQL
$('.set-sql').on('click', function() {
  $('#builder').queryBuilder('setRulesFromSQL', 'name NOT LIKE "Mistic%" AND price BETWEEN 100 AND 200 AND (category IN(1, 2) OR rate <= 2)');
});

// reset builder
$('.reset').on('click', function() {
  $('#builder').queryBuilder('reset');
  $('#result').addClass('hide').find('pre').empty();
});

// get rules
$('.parse-json').on('click', function() {
  $('#result').removeClass('hide')
    .find('pre').html(JSON.stringify(
      $('#builder').queryBuilder('getRules'),
      undefined, 2
    ));
});

$('.parse-sql').on('click', function() {
  var res = $('#builder').queryBuilder('getSQL', $(this).data('stmt'), false);
  $('#result').removeClass('hide')
    .find('pre').html(
      res.sql + (res.params ? '\n\n' + JSON.stringify(res.params, undefined, 2) : '')
    );
});

$('.parse-mongo').on('click', function() {
  $('#result').removeClass('hide')
    .find('pre').html(JSON.stringify(
      $('#builder').queryBuilder('getMongo'),
      undefined, 2
    ));
});

// set filters
$('.set-filters').on('click', function() {
  $(this).prop('disabled', true);
  
  // add a new filter after 'state'
  $('#builder').queryBuilder('addFilter',
    {
      id: 'new_one',
      label: 'New filter',
      type: 'string'
    },
    'state'
  );
  
  // remove filter 'coord'
  $('#builder').queryBuilder('removeFilter',
    'coord',
    true
  );
  
  // also available : 'setFilters'
});
//$('.selectpicker').selectpicker({noneSelectedText:'请选择'});
</script>

</body>
</html>
