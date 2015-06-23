function getAjaxUrl(){
  var form=document.forms[0] ;
  var url=getPath();
  var tbsprodid=form.tbsprodid.value;
  url=url+"/ajax.do?method=selectSizebox&tbsprodid=";
  url=url+tbsprodid;
  return url;
}



  var formatColor=function(value){
     for(var i=0;i<colorstore.getCount();i++){
              var record=colorstore.getAt(i);
              if(value==record.get("label") || value==record.get("value")){
                return record.get("label");
              }
     }
     return value;
  }

   var formatSize=function(value){
     for(var i=0;i<sizestore.getCount();i++){
              var record=sizestore.getAt(i);
              if(value==record.get("label") || value==record.get("value")){
                return record.get("label");
              }
     }
     return value;
  }

  
    function afteredit(el){
       var flag=0;
       if(el.column==1){
           for(var i=0;i<colorstore.getCount();i++){
              var record=colorstore.getAt(i);
              if(record.get("label")==el.value || record.get("value")==el.value){
                 el.record.set("tbscolorid",record.get("value"));
              }
           }
       }else if(el.column==2){//修改了配码
           for(var i=0;i<sizestore.getCount();i++){
              var record=sizestore.getAt(i);
              if(record.get("label")==el.value || record.get("value")==el.value){
                 el.record.set("tbssizeid",record.get("value"));
              }
           }
       } 
    }
var color_model=[]; 
var colorstore=null;
var size_model=[]; 
var sizestore=null;
var grid_model=[]; 
var grid=null;
var grid_store=null; 
Ext.onReady(function(){
  //颜色

  colorstore = new Ext.data.SimpleStore({
        fields: ['value', 'label'],
        data : color_model
  });
  //尺码
  sizestore = new Ext.data.SimpleStore({
        fields: ['value', 'label'],
        data : size_model
  });
  //表格
  var fm = Ext.form;
  var grid_row = Ext.data.Record.create([
           {name: 'rowid', type: 'float'},
           {name: 'tbscolorid', type: 'string'},
           {name: 'colorname', type: 'string'},
           {name: 'tbssizeid', type: 'string'},
           {name: 'sizename',type:'string'},
           {name: 'barcode', type: 'string'},
           {name: 'standardcode', type: 'string'}
  ]);
  grid_store = new Ext.data.Store({ 
    proxy: new Ext.data.MemoryProxy(grid_model), 
    reader: new Ext.data.ArrayReader({},grid_row) 
  }); 
  grid_store.load();
  var cm = new Ext.grid.ColumnModel([{
           header: '行号',
           dataIndex: 'rowid',
           width: 50
        },{
           header:'颜色',
           dataIndex: 'colorname',
           width: 80,
           renderer:formatColor,
           editor: new Ext.form.ComboBox({
               store: colorstore,
               displayField:'label',
               triggerAction: 'all',
               selectOnFocus:true,
               mode: 'local',
               typeAhead: true,
               listClass: 'x-combo-list-small'
            })
        },{
           header:'尺码',
           dataIndex: 'sizename',
           width: 80,
           renderer:formatSize,
           editor: new Ext.form.ComboBox({
               store: sizestore,
               displayField:'label',
               triggerAction: 'all',
               selectOnFocus:true,
               mode: 'local',
               typeAhead: true,
               listClass: 'x-combo-list-small'
            })
        },{
           header: '条形码',
           dataIndex: 'barcode',
           width: 150,
           editor: new fm.TextField({ 
               allowBlank: true
           })
        },{
           header: '国标码',
           dataIndex: 'standardcode',
           width: 150,
           editor: new fm.TextField({ 
               allowBlank: true
           })
        }
    ]);
     var addRow=function(){
	     var p = new grid_row({  
	         rowid:grid.getStore().getCount()+1,
	         tbscolorid:'', 
	         tbssizeid:'',
			 barcode:'',
			 standardcode:''
	     }); 
	     grid.stopEditing(); 
	     grid_store.insert(grid.getStore().getCount(),p); 
	     grid.startEditing(0, 0);
     }
     
     var toolbar=new Ext.Toolbar({
            cls:'top-toolbar',
            items:['条码维护','-'
            ,{
                iconCls: 'icon-add-row',
				text: '增行',
                handler:addRow
            }                    
			]
        });

     grid= new Ext.grid.EditorGridPanel({
        store: grid_store,
        cm: cm,
        width:560,
        height:360,
        renderTo: 'barcode_grid',
        frame:true,
        tbar: toolbar
     });
     grid.on('afteredit',afteredit);
  });   
