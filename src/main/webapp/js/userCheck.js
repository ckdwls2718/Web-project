/**
 * 
 */
 
 function member_check(){
	if(!isKor(mf.name)){
		alert('이름은 한글 이름만 가능합니다');
		mf.name.select();
		return;
	}
	if(!isUserid(mf.userid)){
		alert('아이디는 영문자, 숫자, _ , !fh 4~8자까지 가능해요');
		mf.userid.select();
		return;
	}
	alert('통과');
	//mf.submit();
}
/**
^ : 시작을 의미
$ : 끝을 의미
가-힣 : 한글
+ : 패턴이 한번 이상 반복된다는 의미

 */
 
function isUserid(input){
	let val = input.value;
	let pattern = /^[a-zA-Z0-9_!]+$/; //a or b or c
	let b = pattern.test(val);
	return b;
}

function isKor(input){
	let val = input.value;
	//let pattern = new RegExp(/multi/g);//multi문자열이 있는지 여부를 체크하는 패턴
	//let pattern =/multi/g //위랑 동일
	let pattern = /^[가-힣]+$/
	let b = pattern.test(val); // 정규식 패턴에 맞으면 true를 반환하고, 틀리면 false를 반환한다.
	//alert(b);
	return b;
}