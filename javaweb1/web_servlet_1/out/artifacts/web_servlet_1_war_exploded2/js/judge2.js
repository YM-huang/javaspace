function disableElement(obj)
{
    if(obj.value=="checkbox0")
    {
        document.getElementById("3").disabled=true;
        document.getElementById("4").disabled=true;
        document.getElementById("5").disabled=true;
        document.getElementById("6").disabled=true;
        document.getElementById("7").disabled=true;
        document.getElementById("8").disabled=true;
    }
    else if(obj.value=="checkbox1")
    {
        document.getElementById("2").disabled=true;
    }
}
function renewElement(obj)
{
    if(obj.value=="checkbox0")
    {
        document.getElementById("3").disabled=false;
        document.getElementById("4").disabled=false;
        document.getElementById("5").disabled=false;
        document.getElementById("6").disabled=false;
        document.getElementById("7").disabled=false;
        document.getElementById("8").disabled=false;
    }
    else if(obj.value=="checkbox1")
    {
        document.getElementById("2").disabled=false;
    }
}
function custCheck()
{
    var judgecity=document.getElementsByName("0");
    var judgepatient=document.getElementsByName("1");
    var checkbox=document.getElementsByName("2");
    var agreement=document.getElementsByName("3");
    if(judgecity[0]==true)alert("1");
    if(judgecity[0]==false)alert("2");
    if(!judgecity[0].checked&&!judgecity[1].checked)
    {
        alert("第1题是必选题，请确认是否填写");
        return false;
    }
    else if(!judgepatient[0].checked&&!judgepatient[1].checked)
    {
        alert("第2题是必选题，请确认是否填写");
        return false;
    }
    else if(!checkbox[0].checked&&!checkbox[1].checked&&!checkbox[2].checked&&!checkbox[3].checked
        &&!checkbox[4].checked&&!checkbox[5].checked&&!checkbox[6].checked)
    {
        alert("第3题是必选题，请确认是否填写");
        return false;
    }
    else if(checkbox[0].checked&&(checkbox[1].checked||checkbox[2].checked||checkbox[3].checked
        ||checkbox[4].checked||checkbox[5].checked||checkbox[6].checked))
    {
        alert("第3题填写无效，请重新填写");
        return false;
    }
    else if(!agreement[0].checked||!agreement[1].checked)
    {
        alert("第4题是必选题，请确认是否同意承诺");
        return false;
    }
}