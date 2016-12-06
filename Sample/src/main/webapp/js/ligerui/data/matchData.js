var breeddata =
[
    { text: '育种目标',isexpand:true, children: [ 
		{url:"match/objective/list",text:"育种目标"}
	]
    },
    { text: '组合管理', isexpand: true, children: [
		{url:"match/matchGroup/addGroup",text:"制定组合"},
		{url:"match/matchGroup/list",text:"组合管理"},
		/*{url:"match/verifyMatchGroup/list",text:"组合审核"},*/
		{url:"match/lookGroup/list",text:"组合进度查看"},
		{url:"match/matchGroup/parentMaterial",text:"查看亲本材料"}
	]
    },
    { text: '鉴定计划', isexpand: true, children: [
		{url:"match/appraisePlan/list",text:"鉴定计划"},
		{url:"match/appraisePlan/lookPlan",text:"查看鉴定数据"}
	]
    },
    { text: '计算组合配合力', isexpand: true, children: [
		{url:"match/appraisePlan/chooseSeed",text:"计算组合配合力"}
	]
    }
];