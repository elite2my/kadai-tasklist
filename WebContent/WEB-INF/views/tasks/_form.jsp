<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<lable for="title">タイトル</lable><br />
<input type="text" name="title" value="${task.title}" />
<br /><br />
<lable for="content">タスク</lable><br />
<input type="text" name="content" value="${task.content}" />
<br /><br />

<input type="hidden" name="_token" value="${_token}" />
<button type="submit">確定</button>
