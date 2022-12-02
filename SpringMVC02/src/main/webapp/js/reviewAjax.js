$(function(){
    show_reviews(); //전체리뷰 목록 가져오기
    review_count(); //리뷰 개수 가져오기

    //파일 업로드시 => FormData 객체에 form data를 담아 보내야 한다.
    $('#rf').submit(function(evt){
        evt.preventDefault();
        
        const file = $('#mfilename')[0];

        //첨부 파일명
        const fname = file.files[0];

        const userid = $('#userid').val();
        const content = $('#content').val();
        const score = $('input[name="score"]:checked').val();
        const pnum_fk = $('#pnum_fk').val();
        console.log(userid+"/"+content+"/"+score+"/"+pnum_fk+"/"+fname);

        let fd=new FormData();
        fd.append('mfilename',fname);
        fd.append('userid',userid);
        fd.append('content',content);
        fd.append('score',score);
        fd.append('pnum_fk',pnum_fk);

        let url = "user/reviews";

        /*
       processData:false, ==> 기본값: true ==> true면 enctype="application/x-www-form-urlencoded"타입으로 전송한다.
       contentType:false, ==> 기본값: true ==> true enctype="application/x-www-form-urlencod"
       파일 업로드시는 enctype="multipart/form-data"로 가야 하므로, false, false로 설정하자
        */

        $.ajax({
            type:'post',
            url:url,
            data:fd, //FormData객체 전달
            dataType:'xml',
            processData:false,
            contentType:false,
            cache:false,
            beforeSend:function(xhr){
                xhr.setRequestHeader('Ajax','true');
            },
            success:function(res){
                //alert(res);
                let result = $(res).find('result').text();
                if(result>0){
                    //$('#reviewList').html('<h1>등록성공</h1>');
                    show_reviews();
                } else{
                    alert('등록 실패');
                }
            },
            error:function(err){
                //alert('err : '+err.status);
                if(err.status==400){
                    alert('로그인해야 이용가능함');
                }
            }
        });
    })

    //리뷰 수정 처리
    $('#rf2').submit(function(evt){
        evt.preventDefault();
        //사용자가 수정한 값 얻기
        let uid=rf2.userid.value;
        let pnum=rf2.pnum_fk.value;
        let num=rf2.num.value;
        let score=rf2.score.value;
        let content=rf2.content.value;

        let jsonData={
            userid : uid,
            pnum_fk : pnum,
            num : num,
            score: score,
            content : content
        }

        let data = JSON.stringify(jsonData);
        let url = "user/reviews/"+num;
        $.ajax({
            type:'put',
            url:url,
            data:data,
            contentType:'application/json; charset=utf-8',
            dataType:'json',
            cache:false,
            beforeSend:function(xhr){
                xhr.setRequestHeader('Ajax','true');
            },
            success:function(res){
                if(res.result>0){
                    show_reviews();
                    $('#reviewModal').modal('hide');
                } else {
                    alert('수정 실패');
                }
            },
            error:function(err){
                //alert('err : '+err.status);
                if(err.status==400){
                    alert('로그인해야 해요');
                }
            }
        })


    })

})//$() end---------

//리뷰 개수 가져오기
const review_count=function(){
  let url = 'reviewCnt';
  $.ajax({
    type:'get',
    url:url,
    dataType:'json',
    cache:false,
    success:function(res){
        //alert(res.count);
        $('#review_cnt').html(res.count);
    },
    error:function(err){
        alert('err : '+err.status);
    }
  })
}

//리뷰 목록 가져오기
const show_reviews = function(){
    let url="reviews";
    $.ajax({
        type:'get',
        url:url,
        dataType:'json',
        cache:false,
        success:function(res){
            //alert(JSON.stringify(res));
            showTable(res);
        },
        error:function(err){
            alert('err : '+err.status);
        }
    });
}

const showTable = function(res){
    let str ='<table class="table table-striped">';
    $.each(res,function(i,rvo){
        let d= new Date(rvo.wdate);
        let dstr=d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
        str+='<tr><td width="15%">';
        if(rvo.filename ==null){
            str+='<img src="resources/review_images/noimage.png" class="img-thumbnail" style="width:80%;margin:auto">';
        }else{
            str+='<img src="resources/review_images/'+rvo.filename+'" class="img-thumbnail" style="width:80%;margin:auto">';
        }
        str+='</td><td width="60%" class="text-left">';
        str+= rvo.content+" <span class='float-right'>"+rvo.userid+"["+dstr+"]</span>";
        str+='</td><td width="25%" class="text-left">';
        for(let k=0;k<rvo.score;k++){
            str+='<img src="resources/review_images/star.jpg">';
        }
        str+='<div class="mt-4">';
        if(rvo.userid == "${loginUser.userid}"){
            str+='<a href="#reviewList" onclick="reviewEdit('+rvo.num+')">Edit</a> | ';
            str+='<a href="#reviewList" onclick="reviewDel('+rvo.num+')">Del</a>';
        }
        str+='</div></td>';
        str+='</tr>';
    });
    str+='</table>';
    
    $('#reviewList').html(str);
}

const reviewEdit = function(num){
    
    let url = "user/reviews/"+num;
    $.ajax({
        type:'get',
        url:url,
        dataType:'json',
        cache:false,
        beforeSend:function(xhr){
            xhr.setRequestHeader('Ajax','true');
        },
        success:function(res){
            //alert(JSON.stringify(res));
            rf2.content.value=res.content;
            rf2.num.value=res.num;
            $("#rf2 input:radio[value='"+res.score+"']").prop('checked', true); // 라디오박스 체크하기
            let str="";
            for(let i=0;i<res.score;i++){
                str+='<img src="resources/review_images/star.jpg">';
            }

            $('#star').html(str);

            let imgSrc;
            if(res.filename==null){
                imgSrc = 'noimage.png';
            } else {
                imgSrc = res.filename;
            }

            str="<img src='resources/review_images/"+imgSrc+"' class='img-fluid' >";
            $('#prodImage').html(str);

            //모달창 띄우기
            $('#reviewModal').modal();
        },
        error:function(err){

            // alert('err : '+err.status);
            if(err.status==400){

                alert('로그인해야 가능해요');
            }
        }
    })
}

const reviewDel = function(num){
    let url ="user/reviews/"+num;

    $.ajax({
        type:'delete',
        url:url,
        dataType:'json',
        cache:false,
        beforeSend:function(xhr){
            xhr.setRequestHeader('Ajax','true');
        },
        success:function(res){
            if(res.result>0){
                show_reviews();
            } else{
                alert('삭제 실패');
            }
        },
        error:function(err){
            //alert('err : '+err.status);
            if(err.status==400){
                alert('로그인 해야 이용가능해요');
            }
        }
    })
}

const send = function(){
    //alert('send');
    let params = $('#rf').serialize();
    //alert(params);

    let url = "user/reviews";
    $.ajax({
        type:'post',
        url:url,
        data:params,
        cache:false,
        dataType:'xml',
        success:function(res){
            //alert(res);
            let result = $(res).find('result').text();
            alert(result);
        },
        error:function(err){
            alert('err : '+err.status);
        }
    });
}