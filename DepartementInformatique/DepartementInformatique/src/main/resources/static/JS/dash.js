
document.addEventListener('DOMContentLoaded', function () {
    var modeSwitch = document.querySelector('.mode-switch');
    modeSwitch.addEventListener('click', function () { 
        document.documentElement.classList.toggle('dark');
        modeSwitch.classList.toggle('active');
    });
    var listView = document.querySelector('.list-view');
    var gridView = document.querySelector('.grid-view');
    var projectsList = document.querySelector('.project-boxes');
    
    listView.addEventListener('click', function () {
        gridView.classList.remove('active');
        listView.classList.add('active');
        projectsList.classList.remove('jsGridView');
        projectsList.classList.add('jsListView');
    });

    gridView.addEventListener('click', function () {
        gridView.classList.add('active');
        listView.classList.remove('active');
        projectsList.classList.remove('jsListView');
        projectsList.classList.add('jsGridView');
    });
    document.querySelector('.messages-btn').addEventListener('click', function () {
        document.querySelector('.messages-section').classList.add('show');
    });

    document.querySelector('.messages-close').addEventListener('click', function() {
        document.querySelector('.messages-section').classList.remove('show');
    });
    });

   
/*var projects = [
    {
        date: "10 Mars 2024",
        title: "Design web",
        subheader: "Prototypage",
        progress: "60%",
        participants: ["https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2550&q=80", "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MTB8fG1hbnxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=900&q=60"],
        progressColor: "grey",
        color: "lightgrey",
    },
    {
        date: "10 Mars 2024",
        title: "Design web",
        subheader: "Prototypage",
        progress: "30%",
        participants: ["https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=2550&q=80", "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixid=MXwxMjA3fDB8MHxzZWFyY2h8MTB8fG1hbnxlbnwwfHwwfA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=900&q=60"],
        progressColor: "#B22222",
        color: "#be6e68",
    },
];

function generateProjectHTML(project) {
    return `
        <div class="project-box-wrapper">
            <div class="project-box" style="background-color: ${project.color};">
                <div class="project-box-header">
                    <span>${project.date}</span>
                    <div class="more-wrapper">
                        <button class="project-btn-more">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-vertical">
                                <circle cx="12" cy="12" r="1" />
                                <circle cx="12" cy="5" r="1" />
                                 <circle cx="12" cy="19" r="1" />
                            </svg>
                        </button>
                    </div>
                </div>
                <div class="project-box-content-header">
                    <p class="box-content-header">${project.title}</p>
                    <p class="box-content-subheader">${project.subheader}</p>
                </div>
                <!-- Autres éléments du contenu -->
                <div class="box-progress-wrapper">
                    <p class="box-progress-header">Progression</p>
                    <div class="box-progress-bar">
                        <span class="box-progress" style="width: ${project.progress}; background-color: ${project.progressColor}"></span>
                    </div>
                    <p class="box-progress-percentage">${project.progress}</p>
                </div>
                <div class="project-box-footer">
                <div style="margin-top: 10px;">
                <a href="/telecharger_projet/mon_projet.zip" download style="text-decoration: none">
                    <svg width="20px" height="20px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" stroke="#000000"><g id="SVGRepo_bgCarrier" stroke-width="0"></g>
                    <g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <path opacity="0.5" fill-rule="evenodd" clip-rule="evenodd" d="M3 14.25C3.41421 14.25 3.75 14.5858 3.75 15C3.75 16.4354 3.75159 17.4365 3.85315 18.1919C3.9518 18.9257 4.13225 19.3142 4.40901 19.591C4.68577 19.8678 5.07435 20.0482 5.80812 20.1469C6.56347 20.2484 7.56459 20.25 9 20.25H15C16.4354 20.25 17.4365 20.2484 18.1919 20.1469C18.9257 20.0482 19.3142 19.8678 19.591 19.591C19.8678 19.3142 20.0482 18.9257 20.1469 18.1919C20.2484 17.4365 20.25 16.4354 20.25 15C20.25 14.5858 20.5858 14.25 21 14.25C21.4142 14.25 21.75 14.5858 21.75 15V15.0549C21.75 16.4225 21.75 17.5248 21.6335 18.3918C21.5125 19.2919 21.2536 20.0497 20.6517 20.6516C20.0497 21.2536 19.2919 21.5125 18.3918 21.6335C17.5248 21.75 16.4225 21.75 15.0549 21.75H8.94513C7.57754 21.75 6.47522 21.75 5.60825 21.6335C4.70814 21.5125 3.95027 21.2536 3.34835 20.6517C2.74643 20.0497 2.48754 19.2919 2.36652 18.3918C2.24996 17.5248 2.24998 16.4225 2.25 15.0549C2.25 15.0366 2.25 15.0183 2.25 15C2.25 14.5858 2.58579 14.25 3 14.25Z" fill="#000000"></path> <path fill-rule="evenodd" clip-rule="evenodd" d="M12 16.75C12.2106 16.75 12.4114 16.6615 12.5535 16.5061L16.5535 12.1311C16.833 11.8254 16.8118 11.351 16.5061 11.0715C16.2004 10.792 15.726 10.8132 15.4465 11.1189L12.75 14.0682V3C12.75 2.58579 12.4142 2.25 12 2.25C11.5858 2.25 11.25 2.58579 11.25 3V14.0682L8.55353 11.1189C8.27403 10.8132 7.79963 10.792 7.49393 11.0715C7.18823 11.351 7.16698 11.8254 7.44648 12.1311L11.4465 16.5061C11.5886 16.6615 11.7894 16.75 12 16.75Z" fill="#000000"></path> </g></svg>
                </a>
            </div>
                                
                </div>
            </div>
        </div>
    `;
}

function addProjectsToPage() {
    var projectContainer = document.querySelector('.project-boxes');
    projects.forEach(function(project) {
        var projectHTML = generateProjectHTML(project);
        projectContainer.innerHTML += projectHTML;
    });
}

addProjectsToPage();
*/
document.addEventListener("DOMContentLoaded", function() {
    const sidebarLinks = document.querySelectorAll(".app-sidebar-link");

    const projectSections = document.querySelectorAll(".projects-section");

    function toggleActiveClass(link) {
        sidebarLinks.forEach(function(item) {
            item.classList.remove("active");
        });
        link.classList.add("active");
    }

    function showHideSections(sectionId) {
        projectSections.forEach(function(section) {
            if (section.id === sectionId) {
                section.classList.add("active");
                section.style.display = "block";
            } else {
                section.classList.remove("active");
                section.style.display = "none"; 
            }
        });
    }

    sidebarLinks.forEach(function(link) {
        link.addEventListener("click", function(event) {
            event.preventDefault();
            const sectionId = this.getAttribute("href").substring(1);
            toggleActiveClass(this);
            showHideSections(sectionId);
        });
    });
});

document.addEventListener("DOMContentLoaded", function() {
    var eleve = document.getElementById("eleve");
  
    var profileBtn = document.querySelector(".profile-btn");
  
    var dropdown = profileBtn.querySelector(".dropdown-content");
  
    eleve.addEventListener("click", function(event) {
      event.stopPropagation(); // Empêcher la propagation de l'événement de clic pour éviter que le dropdown ne se cache immédiatement
      toggleDropdown();
    });
  
    document.addEventListener("click", function(event) {
      var isClickInside = profileBtn.contains(event.target); 
      if (!isClickInside) {
        dropdown.style.display = "none";
      }
    });
  
    function toggleDropdown() {
      if (dropdown.style.display === "block") {
        dropdown.style.display = "none";
      } else {
        dropdown.style.display = "block";
      }
    }
  });

$(document).ready(function() {
    $(".notification-drop .item").on('click',function() {
        $(this).find('ul').toggle();
    });
});