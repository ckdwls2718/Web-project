<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Ajax 요청 처리- -->    
<!-- <script src="js/reviewAjax.js"></script> -->
<!-- --------------- -->

<script>
<%@ include file="/js/reviewAjax.js"%>
</script>

<form method="post" enctype="multipart/form-data" name="rf" id="rf">
	<!-- hidden data------------------------------------ -->
		<input type="hidden" name="pnum_fk" id="pnum_fk" value="${prod.pnum}">
		<input type="hidden" name="userid" id="userid" value="${loginUser.userid}">
	<!-- ---------------------------------------------- -->
	<table class="table">
	<tr>
		<th colspan="4" class="text-center">
		<h3>::상품 후기 쓰기::</h3>
		</th>
	</tr>
	<tr>
		<th>평가점수</th>
		<td>
		<!-- <input type="hidden" name="score" id="score"> -->
		 <label for="score1"><input type="radio" name="score" id="score1" value="1">1점</label>
		<label for="score2"><input type="radio" name="score" id="score2" value="2">2점</label>
		<label for="score3"><input type="radio" name="score"  id="score3" value="3">3점</label>
		<label for="score4"><input type="radio" name="score" id="score4" value="4">4점</label>
		<label for="score5"><input type="radio" name="score"  id="score5"value="5">5점</label>
		
		
		</td>
		<th>작성자</th>
		<td>${loginUser.name}[${loginUser.userid}]</td>
	</tr>	
	<tr>
		<th>상품평</th>
		<td colspan="3">
		<textarea name="content" id="content"
		 rows="2" class="form-control"></textarea>	
		</td>
	</tr>
	<tr>
		<th>이미지업로드</th>
		<td colspan="2">
		<input type="file" name="mfilename" id="mfilename"
		 accept="image/*" class="form-control">
		</td>
		<td>
		<!-- 파일 업로드가 없는 일반적인 폼이라면 데이터를 전송할 때 => send()함수 통해 ajax요청 -->
		<!-- <a type="button" class="btn btn-success"
				onclick="send()">글쓰기</a> -->
		<button class="btn btn-outline-success">글쓰기</button>
		</td>
	</tr>
</table>
</form>