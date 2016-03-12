'use strict';

define(['jquery','bootbox','bootstrap','underscore','jquicustom','jqui','jqchosen','gritter','WdatePicker'], function ($) {


  function noticeDefault(titile,text,iscenter){
    $.gritter.add({
      title: titile,
      text: text, 
      sticky: false,
      time: 2500,
      class_name:'' + (iscenter ? ' gritter-center' : '')
    });
  }

  function noticeInfo(titile,text,iscenter){
    var unique_id = $.gritter.add({
      title: titile,
      text: text, 
      sticky: false,
      time: 2500,
      class_name: 'gritter-info' + (iscenter ? ' gritter-center' : '')
    });
  }

  function noticeSuccess(titile,text,iscenter){
    $.gritter.add({ 
      title: titile, 
      text: text,
      sticky: false,
      time: 2500,
      class_name: 'gritter-success' + (iscenter ? ' gritter-center' : '')
    });
  }

  function noticeWarning(titile,text,iscenter){
    $.gritter.add({ 
      title: titile, 
      text: text,
      sticky: false,
      time: 2500,
      class_name: 'gritter-warning' + (iscenter ? ' gritter-center' : '')
    });
  }

  function noticeError(titile,text,iscenter){
    console.info("noticeError");
    $.gritter.add({ 
      title: titile, 
      text: text,
      sticky: false,
      time: 2500,
      class_name: 'gritter-error' + (iscenter ? ' gritter-center' : '')
    });
  }

  function dialogCheck(msg,cb,cc){
    //提示框开始
      bootbox.dialog({
        message:msg,
        title: "<i class='fa fa-warning' style='padding-right:10px;color:#D84A38;font-size:22px;'></i>"+"审核信息",
        buttons: {
          cancel: {
            className: "btn-danger btn-xs",
            label: "审核不通过",
            callback: cc
          },
          main: {
            label: "审核通过",
            className: "btn-success btn-xs",
            callback: cb
          }
        }
      });
    //提示框结束
  }
  
  function dialogSettingPrice(cb,cc){
	  //提示框开始
	  bootbox.dialog({
		  message:"价格：<input id='PAY_PRICE' class='input-xlarge' maxlength='10'/>",
		  title: "<i class='fa fa-warning' style='padding-right:10px;color:#D84A38;font-size:22px;'></i>"+"设置价格",
		  buttons: {
			  cancel: {
				  className: "btn-danger",
				  label: "取消",
				  callback: cc
			  },
			  main: {
				  label: "确定",
				  className: "btn-success",
				  callback: function(){
					  var price = $("#PAY_PRICE").val();
					  if(""!=$.trim(price)){
						  if(isNaN(price)){
							  noticeError("错误","价格须为数字！",false);
						  }else{
							  cb(price);
						  }
					  }else{
						  noticeError("错误","请输入价格！",false);
						  return false;
					  }
				  }
			  }
		  }
	  });
	  //提示框结束
  }
  
  function dialogPrompt(title,msg,cb,cc){
	  //提示框开始
	  bootbox.dialog({
		  message:msg,
		  title:title,
		  buttons: {
			  main: {
				  label: "确定",
				  className: "btn-success",
				  callback: function(result){
					  cb(result);
				  }
			  },
			  cancel: {
				  className: "btn-danger",
				  label: "取消",
				  callback: cc
			  }
		  }
	  });
	  //提示框结束
  }
  
  function dialogError(msg,cb){
    //提示框开始
      bootbox.dialog({
        message:msg,
        title: "<i class='fa fa-warning' style='padding-right:10px;color:#D84A38;font-size:22px;'></i>"+"警告信息",
        buttons: {
          cancel: {
            className: "btn-info btn-xs",
            label: "取消"
          },
          main: {
            label: "确定",
            className: "btn-danger btn-xs",
            callback: cb
          }
        }
      });
    //提示框结束
  }
  function dialogFailure(msg,cb){
    //回调提示1 begin
    bootbox.dialog({            
      message: msg,
      title: "提示",
      buttons: {
        danger: {
          label: "知道了!",
          className: "btn-danger btn-xs",
          callback: cb
        }
      }
    });
    //回调提示1 end
  }

  function dialogSuccess(msg,cb){
    //回调提示2 begin
    bootbox.dialog({            
      message:msg,
      title: "提示",
      buttons: {
        success: {
          label: "确定",
          className: "btn-success btn-xs",
          callback: cb
        }
      }
    });
    //回调提示2 end
  }


  function pageView(eid){
    // var oTable1 = 
    //     $(eid)
    //     .dataTable( {
    //       bAutoWidth: false, 
    //       "ordering": false,
    //       "searching": false,
    //       "pagingType": "full_numbers",
    //       "language":{
    //         paginate:{
    //           "first": "首页",
    //           "last" : "尾页",
    //           "next" : "下一页",
    //           "previous": "上一页"
    //         },
    //         info:"第\_PAGE\_页,共\_PAGES\_页,\_TOTAL\_条数据"
    //       },
    //       "lengthChange": false
    //       });

        $(document).on('click', 'th input:checkbox' , function(){
          var that = this;
          $(this).closest('table').find('tr > td:first-child input:checkbox')
          .each(function(){
            this.checked = that.checked;
            $(this).closest('tr').toggleClass('selected');
          });
        });

        $('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
        function tooltip_placement(context, source) {
          var $source = $(source);
          var $parent = $source.closest('table');
          var off1 = $parent.offset();
          var w1 = $parent.width();
      
          var off2 = $source.offset();
          //var w2 = $source.width();
      
          if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
          return 'left';
        }

  }


  function rangedate(obj){
    //"jQuery UI Slider"
    //range slider tooltip example
      // $(obj).each(function(index, el) {
         // console.info(index);
         return $(obj).slider({ 
            range: true,
            min: 1,
            max: 24,
            values: [ 8, 12 ],
            slide: function( event, ui ) {
              var val = ui.values[$(ui.handle).index()-1];
              if(val < 10){
                val = "0"+val;
              }
              val += ":00";
              if( !ui.handle.firstChild ) {
                $("<div class='tooltip bottom in' style='display:none;left:-15px;top:15px;'><div class='tooltip-arrow'></div><div class='tooltip-inner'></div></div>")
                .prependTo(ui.handle);
              }
              $(ui.handle.firstChild).show().children().eq(1).text(val);
            }
          }).find('a').on('blur', function(){
            $(this.firstChild).hide();
          });
      // });
   
  }

  function rangeval(obj){ 
    return $(obj).slider( 'values');
  }
  function setrangeval(obj,vals){ 
    return $(obj).slider( 'values',vals);
  }

  function chosenMuti(obj){
      $('.chosen-select').chosen({allow_single_deselect:true});

    $(window)
        .off('resize.chosen')
        .on('resize.chosen', function() {
            $('.chosen-select').each(function() {
                var $this = $(this);
                $this.next().css({'width': $this.parent().width()});
            });
        }).trigger('resize.chosen');
  }
  
  function formatDateYMd(time){
      var  year=time.getFullYear();    
      var  month=time.getMonth()+1;  
      if(month<10) month = "0"+month;
      var date=time.getDate(); 
      if(date<10) date = "0"+date;
      return   year+"-"+month+"-"+date;   
  }; 
  
  function formatDateYMdHms(time) {
      var  year=time.getFullYear();     
      var  month=time.getMonth()+1;
      if(month<10) month = "0"+month;
      var  date=time.getDate();  
      if(date<10) date = "0"+date;
      var  hour=time.getHours();   
      if(hour<10) hour = "0"+hour;
      var  minute=time.getMinutes();  
      if(minute<10) minute = "0"+minute;
      var  second=time.getSeconds();
      if(second<10) second = "0"+second;
      return   year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;   
  }; 

  return {

    noticeDefault:noticeDefault,
    noticeInfo:noticeInfo,
    noticeSuccess:noticeSuccess,
    noticeWarning:noticeWarning,
    noticeError:noticeError,
    
    dialogCheck:dialogCheck,
    dialogError:dialogError,
    dialogFailure:dialogFailure,
    dialogSuccess:dialogSuccess,
    dialogSettingPrice:dialogSettingPrice,
    dialogPrompt:dialogPrompt,
    pageView:pageView,
    rangedate:rangedate,
    rangeval:rangeval,
    setrangeval:setrangeval,
    chosenMuti:chosenMuti,
    formatDateYMd:formatDateYMd,
    formatDateYMdHms:formatDateYMdHms
  };
    
});



     



