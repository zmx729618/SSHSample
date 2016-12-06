var breeddata =
[
    { text: '杂交设计',isexpand:true, children: [ 
		{url:"breed/cross/list",text:"试验管理"},
		{url:"breed/group/cross/list",text:"试验审核"},
		{url:"breed/cross/plotRecord/list",text:"试验数据查看"},
		{url:"match/verifyMatchGroup/list",text:"组合审核"},
		{url:"breed/crossGroup/list",text:"试验组管理"}
	]
    },
    { text: '选种设计', isexpand: true, children: [
		{url:"breed/scheme/list",text:"试验管理"},
		{url:"breed/group/scheme/list",text:"试验审核"},
		{url:"breed/scheme/plotRecord/list",text:"试验数据查看"},
		{url:"scheme/material/evaluate",text:"试验材料评价"},
		{url:"breed/scheme/combine",text:"多点试验组建"},
		{url:"breed/schemeGroup/list",text:"试验组管理"}
	]
    },
    { text: '其他', isexpand: true, children: [
		{url:"breed/schemeTraitTemplate/list",text:"试验考察性状模板"}
	]
    }
];