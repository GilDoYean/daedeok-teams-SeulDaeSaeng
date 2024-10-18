document.addEventListener('DOMContentLoaded', function() {

    // 캘린더 
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth'
    });
    calendar.render();

    // 메모관련
    const taskList =  document.querySelector('.taskList');
    const addList =   document.querySelector('.btn_addList');
    const addListBtn = addList.querySelector('.btn_add');
    const addListCancel = addList.querySelector('.btn_cancel');
    const cancle = document.querySelector('.btn_cancel');
    const listItems =  JSON.parse(localStorage.getItem('items')) || [];
    const taskNav = document.querySelector('.taskNav');

    // AddList
    function TaskAddList(e){
        if( !e.target.matches('.text') || this.classList.contains('active') ) return;
        this.classList.add('active');
    }

    // cancel AddList
    function cancelTask(){
        addList.classList.remove('active');
        const allInput = addList.querySelectorAll('input');
        allInput.forEach( (inputItem) => {inputItem.value = ''});
        addList.querySelector('.control_textarea').value = '';
    }

    // button add
    
    function handleTask(e){
        const text  = addList.querySelector('.addList_input').value;
        const date  = addList.querySelector('[name="deadline_date"]').value;
        const time  = addList.querySelector('[name="deadline_time"]').value;
        const comment  =  addList.querySelector('.control_textarea').value;
        const file = addList.querySelector('input[type=file]').value;
        const star = addList.querySelectorAll('[name="priority"]');
        let starValue = false;
        let complete = false;
        for(i=0;i<star.length;i++){
            if((star[i].checked == true) && (star[i].id == 'yes') ){
                starValue = !starValue;
                break;
            }
        }
        const item = {
            complete,
            text,
            date,
            time,
            comment,
            file,
            starValue,
        } 
        

        
        listItems.push(item);
        
        
        localStorage.setItem('items', JSON.stringify(listItems));   
        populateList(listItems,taskList);
        

        countTask(listItems.length);
        
        cancelTask();  
    }

    // generate list
    function populateList(task,attachTask){
    attachTask.innerHTML = task.map(function(item,i){
        
        return `
        <li  data-index='${i}'  class='${ (item.starValue)  ? "priority" : ""} ${(item.complete) ? "completed" : ""}'  >
            <div class="itemDetail">
                <div class="setTime">${item.date}&emsp;${item.time}</div>
                <input type="checkbox" name="complete" id='ListItem${i}'>
                <label for='ListItem${i}' class="text_oneline">
                    ${item.text}
                </label>
                ${item.comment != '' ? 
                '<div class="itemComment itemIcon"><i class="bi bi-chat-dots"></i>' 
                : ''}
                ${item.file != '' ? 
                '<div class="itemFile itemIcon"><i class="bi bi-star-fill"></i>' 
                : ''}
            </div>
            <div class="itemChange" >
                <div class="itemStar itemMark">
                    <i class="bi bi-star-fill"></i>
                </div>
                <div class="itemEdit itemMark">
                    <i class="bi bi-pencil-fill"></i>
                </div>
            </div>
            <div class="addList">
                <div class="deadline">
                    <div class="addList_title">
                        <span class="addList_icon"> <i class="bi bi-calendar-date"></i></span> 마감 일시
                    </div>
                    <div class="addList_control">
                        <input class="control control_input" type="date" name="deadline_date" value="${item.date}">
                        <input class="control  control_input" type="time" name="deadline_time" value=${item.time}>
                    </div>
                </div>
                <div class="addComment">
                    <div class="addList_title">
                        <span class="addList_icon">
                            <i class="bi bi-chat-dots"></i>
                        </span> 메모
                    </div>
                    <div class="addList_control">
                        <textarea name="" class="control  control_textarea" id="" rows="5" 
                        >${item.comment}</textarea>
                    </div>
                    <div class="upload">
                        <div class="addList_title">
                            <span class="addList_icon">
                                <i class="bi bi-star-fill"></i>
                            </span> 파일
                        </div>
                        <div class="addList_control">
                            <div class="upload_file">
                                <input type="file" name="myfile" value="${item.file}"/>
                            </div>
                        </div>
                    </div>
                    <div class="add-items">
                        <button class="btn btn_cancel"><i class="bi bi-x"></i> 수정</button>
                        <button class="btn btn_add"><i class="bi bi-plus"></i> 삭제</button>
                    </div>
                </div>
            </div>
        </li>
        `
    }).join('');
    }
        
            
    function toggleDone(e){
        toggleComplete(e);
        toggleEdit(e);
        toggleDelete(e);
        toggleModify(e);
    }
    // toogle Complete   
    function toggleComplete(e) {
        let target = e.target.closest('label');
        if (!target) return; // 라벨이 아닌 요소를 클릭한 경우 종료
        let grandfather = target.parentNode.parentNode;
        let index = grandfather.dataset.index;
        listItems[index].complete = !listItems[index].complete;
        localStorage.setItem('items', JSON.stringify(listItems)); 
        populateList(listItems, taskList);
    }         
    function toggleEdit(e){
        // if( !e.target.matches('.btn_cancel') ) return;


        if( !e.target.matches('.bi-pencil-fill') ) return;
        let addList = e.target.parentNode.parentNode.parentNode;
        let modifyIcon = e.target.closest('.itemEdit');
        addList.querySelector('.addList').classList.toggle('act');
        modifyIcon.classList.toggle('act');
    }            
    function toggleDelete(e){
        if( !e.target.matches('.btn_cancel') ) return;
        let modifyIcon = e.target.parentNode.parentNode.parentNode.previousElementSibling;
        let index = modifyIcon.dataset.index; // 삭제하려는 항목의 인덱스 가져오기
        listItems.splice(index, 1); // 배열에서 해당 인덱스의 항목 삭제
        localStorage.setItem('items', JSON.stringify(listItems)); // 업데이트된 배열을 로컬 스토리지에 다시 저장
        
        populateList(listItems, taskList); // 화면에 업데이트된 목록 표시
        countTask(listItems.length);
        
        cancelTask(); 
    }           
    function toggleModify(e){
        if( !e.target.matches('.btn_add') ) return;
        let addList = e.target.closest('.addList');
        console.log(addList.querySelector('[name="deadline_date"]').value);
        let indeList = e.target.parentNode.parentNode.parentNode.parentNode;
        let index = indeList.dataset.index; // 수정하려는 항목의 인덱스 가져오기
        
        // 수정하려는 항목의 내용을 새로운 내용으로 대체
        // listItems[index].text = addList.querySelector('.addList_input').value;


        listItems[index].date = addList.querySelector('[name="deadline_date"]').value;
        listItems[index].time = addList.querySelector('[name="deadline_time"]').value;
        listItems[index].comment = addList.querySelector('.control_textarea').value;
        listItems[index].file = addList.querySelector('input[type=file]').value;
        const star = addList.querySelectorAll('[name="priority"]');
        listItems[index].starValue = false;
        for(i=0;i<star.length;i++){
            if((star[i].checked == true) && (star[i].id == 'yes') ){
                listItems[index].starValue = true;
                break;
            }
        }
    
        localStorage.setItem('items', JSON.stringify(listItems)); // 업데이트된 배열을 로컬 스토리지에 다시 저장
        
        populateList(listItems, taskList); // 화면에 업데이트된 목록 표시
        countTask(listItems.length);
        
        cancelTask(); 
    }      


    //tasknav add active
    function addNavActive(e){
    
        if( !e.target.nodeName == 'A') return;
        e.preventDefault();
        const eachNav = [...taskNav.querySelectorAll('.taskNav_item')];
        eachNav.forEach( item => item.classList.remove('active'));
        e.target.classList.add('active');
        let donelistItem;
        if( e.target.matches('.taskAll')){
            populateList(listItems,taskList);
            countTask(listItems.length);
        }
        
        //task progress
        if( e.target.matches('.taskprogress')){
        donelistItem =  listItems.filter( item => {
            if( !item.complete){
                return item;
            }
        });
            countTask(donelistItem.length);
            populateList(donelistItem,taskList);
        
        }
        
        //task complete
        if( e.target.matches('.taskcompleted')){
        donelistItem =  listItems.filter( item => {
            if(item.complete){
                return item;
            }
        });
            countTask(donelistItem.length);
            populateList(donelistItem,taskList);
        
        }
        
    }

    //totaltask
    function countTask(totalTask){
    document.querySelector('.totaltask_num').textContent = totalTask;
    }

    countTask(listItems.length);


    taskNav.addEventListener('click',addNavActive);   

    addList.addEventListener('click', TaskAddList);
    addListBtn.addEventListener('click', handleTask);
    addListCancel.addEventListener('click',cancelTask);
    taskList.addEventListener('click',toggleDone);

    populateList(listItems,taskList);

});

