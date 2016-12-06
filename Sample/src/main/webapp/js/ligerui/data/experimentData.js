var experimentdata =
[
    { text: '组合筛选',isexpand:true, children: [ 
	    {url:"match/objective/list1",text:"育种目标"},
    	{url:"match/screening/addGroup",text:"制定组合"},
    	{url:"match/screening/input/list",text:"导入组合"},
		{url:"experiment/match/screening/list",text:"组合审核"},
		{url:"match/screening/lookGroup",text:"组合进度查看"},
		{url:"match/screening/group",text:"查看组合详细"},
		{url:"experiment/screening/list",text:"试验管理"},
		{url:"experiment/group/screening/list",text:"试验审核"},
		{url:"experiment/screening/plotRecord/list",text:"试验数据查看"},
		{url:"experiment/screeningGroup/list",text:"试验组管理"}
	]
    },
    { text: '播种预期',isexpand:true, children: [ 
	    {url:"experiment/seeding/list",text:"组合播种预期"},
	    {url:"experiment/seeding/info",text:"组合播种预期查看"},
	    {url:"experiment/seeding/materialList",text:"材料播种预期"},
	    {url:"experiment/seeding/materialInfo",text:"材料播种预期查看"}
	]
    },
    { text: '农艺试验设计',isexpand:true, children: [ 
		{url:"experiment/trial/list",text:"试验管理"},
		{url:"experiment/group/trial/list",text:"试验审核"},
		{url:"experiment/trial/plotRecord/list",text:"试验数据查看"},
		{url:"trial/material/evaluate",text:"试验材料评价"},
		{url:"experiment/trial/combine",text:"多点试验组建"},
		{url:"experiment/trialGroup/list",text:"试验组管理"}
	]
    },   
    { text: '其他', isexpand: true, children: [
		{url:"experiment/traitTemplate/list",text:"试验考察性状模板"}
	]
    }
];