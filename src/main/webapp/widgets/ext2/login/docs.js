Docs = {};
EskPanel = function(node) {
    EskPanel.superclass.constructor.call(this, {
        id:node.id,
        margins:'0 0 5 5',
        cmargins:'0 0 0 0',
        rootVisible:false,
        lines:false,
        autoScroll:true,
        animCollapse:false,
        animate: false,
        loader: new Ext.tree.TreeLoader({
			preloadChildren: true,
			clearOnLoad: false
		}),
        root: new Ext.tree.AsyncTreeNode({
            text:'',
            id:'root',
            iconCls:'icon-root-s',
            expanded:true,
            children:node.children
         }),
        collapseFirst:false
    });
    this.getSelectionModel().on('beforeselect', function(sm, node){
        return node.isLeaf();
    });
};

Ext.extend(EskPanel, Ext.tree.TreePanel, {
    selectClass : function(cls){
       
    }
});


MainPanel = function(){
    MainPanel.superclass.constructor.call(this, {
        id:'doc-body',
        region:'center',
        margins:'0 5 5 0',
        resizeTabs: true,
        minTabWidth: 135,
        tabWidth: 135,
        plugins: new Ext.ux.TabCloseMenu(),
        enableTabScroll: true,
        activeTab: 0,

        items: {
             'id':'homenode',   
              closable:true,
             'title':'工作台',   
              autoScroll:true,
              html:'<iframe id="f_'+id+'" scrolling="auto" frameborder="0" width="100%" height="100%" src="'+getWorkspace()+'"></iframe>'  
        }
    });
};
Ext.extend(MainPanel, Ext.TabPanel, {
    loadClass : function(node,member){
        var href=node.attributes.href;
        var id=node.id;
        var title=node.attributes.text;
        var tab = this.getComponent(id);
        if(tab){
            //this.remove(tab)
            this.setActiveTab(tab);
            if(member){
                tab.scrollToMember(member);
               
            }
            var iframename='f_'+id;
            var iframe=document.frames(iframename);
            iframe.location.href=href;
        }else{
            var autoLoad = {url: href};
            if(member){
                autoLoad.callback = function(){
                    Ext.getCmp(id).scrollToMember(member);
                }
            }
            var p= this.add({   
             'id':id,   
             'title':title,   
              closable:true,
              autoScroll:true,
              html:'<iframe id="f_'+id+'" scrolling="auto" frameborder="0" width="100%" height="100%" src="'+href+'"></iframe>'   
            }); 
            this.setActiveTab(p);
       } 
    }
});
var mainPanel=null;
var api=null;
Ext.onReady(function(){
api= new Ext.Panel({
   title: getTitle(),
   layout:'accordion',
   region:'west',
   body:false,
   width: 220,
   minSize: 175,
   collapsible: true,
   autoScroll:false,
   maxSize: 300,
   margins:'0 0 5 5',
   cmargins:'0 0 0 0',
   layoutConfig: {
       animate: true,
       activeOnTop : false
   }
});
for(var i=0;i<Docs.menuData.length;i++){
   var node=Docs.menuData[i];
   var treeP = new EskPanel(node);
   treeP.on('click', function(n, e){
         if(n.isLeaf()){
            e.stopEvent();
            mainPanel.loadClass(n);
         }
    });
   api.add({ title: node.text,
             autoScroll:true,
             collapsed:true,
             items: [treeP]});
}


   
  
   
    mainPanel = new MainPanel();
    


   var hd = new Ext.Panel({
        border: false,
        layout:'anchor',
        region:'north',
        cls: 'docs-header',
        height:60,
        items: [{
            xtype:'box',
            el:'header',
            border:false,
            anchor: 'none -25'
        },toolbar
       ]
    })


    var viewport = new Ext.Viewport({
        layout:'border',
        items:[ hd, api, mainPanel ]
    });
    viewport.doLayout();
	setTimeout(function(){
        Ext.get('loading').remove();
        Ext.get('loading-mask').fadeOut({remove:true});
    }, 1500);
});

