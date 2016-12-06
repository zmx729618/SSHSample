var breeddata =
[
    { text: '稳定材料管理',isexpand:true, children: [
        {url:"material/Seed",text:"稳定材料列表"},
        {url:"material/seedExcelInput",text:"稳定材料导入"},
		{url:"material/OutRecord",text:"稳定材料出库"}
	]
    },
    { text: '中间材料管理', isexpand: true, children: [
		{url:"material/HarvestMaterial",text:"中间材料列表"},
		{url:"material/harvestMaterialExcelInput",text:"中间材料导入"},
		{url:"material/ExtractMaterial",text:"中间材料提取"}
	]
    },
    { text: '材料分组管理', isexpand: true, children: [
		{url:"material/ParentSeedGroupList/List",text:"亲本材料组管理"},
		{url:"material/CrossSeedGroupList/List",text:"育种材料组管理"},
		{url:"material/ExperimentSeedGroupList/List",text:"试验材料组管理"},
		{url:"material/CheckSeedGroupList/List",text:"对照材料组管理"}
	]
    },
    { text: '库存提醒', isexpand: true, children: [
		{url:"material/SeedState/List",text:"材料库存状态"}
	]
    },
    { text: '数据查看', isexpand: true, children: [
        {url:"material/HistoryDate/List",text:"历史数据查看"}
    ]
    }

];