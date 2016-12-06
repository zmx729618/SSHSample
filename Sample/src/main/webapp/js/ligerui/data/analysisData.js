var analysisdata =
[
    { text: '查看分析记录',isexpand:true, children: [ 
		{url:"analysis/record/list",text:"所有分析记录"}
	]
    },
    { text: '综合评价分析',isexpand:true, children: [ 
		{url:"analysis/dtopsis/selectData",text:"理想品种评价"}
		,{url:"analysis/grey/selectData",text:"性状关联分析"}
	]
    },
    { text: '单因素试验分析',isexpand:false, children: [ 
        {url:"analysis/rb1way/layout",text:"单因素随机区组"}
        ,{url:"analysis/cr1way/layout",text:"单因素完全随机"}	
		,{url:"analysis/contrast/layout",text:"对比法"}
		,{url:"analysis/intervalcontrast/layout",text:"间比法"}	
		,{url:"analysis/nest/layout",text:"巢式试验"}
		,{url:"analysis/latin/layout",text:"拉丁方"}
		//,{url:"analysis/multi_ck_seq/layout",text:"多对照顺序法"}
		//,{url:"analysis/interval_rand/layout",text:"间比随机法"}
	]
    },
    { text: '多因素试验分析',isexpand:false, children: [ 
        {url:"analysis/blockinrep/layout",text:"重复内分组"}
        ,{url:"analysis/rb2way/layout",text:"二因素随机区组"}
        ,{url:"analysis/cr2waysingle/layout",text:"二因素无重复完全随机"}
        ,{url:"analysis/cr2wayrep/layout",text:"二因素有重复完全随机"}
        ,{url:"analysis/split/layout",text:"裂区"}
        ,{url:"analysis/strip/layout",text:"条区"}	
	]
    },
	{ text: '品种区域试验',isexpand:true, children: [ 
		{url:"analysis/regoneyear/layout",text:"一年多点"}
		,{url:"analysis/regmultiyear/layout",text:"多年多点"}
	]
    },
    { text: '基于地块分析',isexpand:true, children: [ 
        {url:"analysis/section/layout",text:"基于地块分析"}
	   ]
	}
];

var treeNo={
	intervalContrastNo:9	
	,nestNo:10
	,latinNo:11
	,regoneyear:20
	,regmultiyear:21		
};