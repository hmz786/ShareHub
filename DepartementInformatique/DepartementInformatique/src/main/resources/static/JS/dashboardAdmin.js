document.addEventListener('DOMContentLoaded', function () {
    var modeSwitch = document.querySelector('.mode-switch');
    modeSwitch.addEventListener('click', function () {
        document.documentElement.classList.toggle('dark');
        modeSwitch.classList.toggle('active');
    });

    var listView = document.querySelector('.list-view');
    var gridView = document.querySelector('.grid-view');
    var projectsList = document.querySelector('.project-boxes');
    var notesList = document.querySelector('.note-boxes');

    listView.addEventListener('click', function () {
        gridView.classList.remove('active');
        listView.classList.add('active');
        projectsList.classList.remove('jsGridView');
        projectsList.classList.add('jsListView');
        notesList.classList.remove('jsGridView');
        notesList.classList.add('jsListView');
    });

    gridView.addEventListener('click', function () {
        gridView.classList.add('active');
        listView.classList.remove('active');
        projectsList.classList.remove('jsListView');
        projectsList.classList.add('jsGridView');
        notesList.classList.remove('jsListView');
        notesList.classList.add('jsGridView');
    });

    document.querySelector('.messages-btn').addEventListener('click', function () {
        document.querySelector('.messages-section').classList.add('show');
    });

    document.querySelector('.messages-close').addEventListener('click', function() {
        document.querySelector('.messages-section').classList.remove('show');
    });

    // Fonction pour générer le HTML des projets
    function generateProjectHTML(project) {
        return `
        <div class="project-box-wrapper">
            <div class="project-box" style="background-color: ${project.color};">
                <div class="project-box-header">
                    <span class="box-content-annee-session">${project.annee_session}</span>
                    <div class="more-wrapper">
                        <button class="project-btn-more">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-vertical">
                                <circle cx="12" cy="12" r="1" />
                                <circle cx="12" cy="5" r="1" />
                                <circle cx="12" cy="19" r="1" />
                            </svg>
                        </button>
                        <div class="dropdown-content project-actions">
                            <a href="#" class="edit-project" data-id="${project.id_projet}">Modifier</a>
                            <a href="#" class="delete-project" data-id="${project.id_projet}">Supprimer</a>
                        </div>
                    </div>
                </div>
                <div class="project-box-content-header">
                    <p class="box-content-header">${project.nom_projet}</p>
                    <p class="box-content-subheader">${project.description}</p>
                    <p class="box-content-url-git" style="display:none;">${project.url_projet_git}</p>
                    <p class="box-content-video" style="display:none;">${project.video}</p>
                </div>
                <div class="project-box-footer">
                    <div style="margin-top: 10px;">
                     
                    </div>
                </div>
            </div>
        </div>
    `;
    }

    // Fonction pour générer le HTML des notes de cours
    function generateNoteHTML(note) {
        return `
        <div class="note-box-wrapper">
            <div class="note-box" style="background-color: ${note.color};">
                <div class="note-box-header">
                    <div class="more-wrapper">
                        <button class="note-btn-more">
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-vertical">
                                <circle cx="12" cy="12" r="1" />
                                <circle cx="12" cy="5" r="1" />
                                <circle cx="12" cy="19" r="1" />
                            </svg>
                        </button>
                        <div class="dropdown-content note-actions">
                            <a href="#" class="edit-note" data-id="${note.id_notes_de_cours}">Modifier</a>
                            <a href="#" class="delete-note" data-id="${note.id_notes_de_cours}">Supprimer</a>
                        </div>
                    </div>
                </div>
                <div class="note-box-content-header">
                    <p class="box-content-header">${note.nom}</p>
                    <p class="box-content-subheader">${note.contenu}</p>
                </div>
            </div>
        </div>
    `;
    }

    // Afficher le menu des actions du projet
    document.addEventListener('click', function (event) {
        const projectTarget = event.target.closest('.project-btn-more');
        if (projectTarget) {
            const dropdown = projectTarget.nextElementSibling;
            dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
        } else {
            const noteTarget = event.target.closest('.note-btn-more');
            if (noteTarget) {
                const dropdown = noteTarget.nextElementSibling;
                dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
            } else {
                document.querySelectorAll('.dropdown-content').forEach(dropdown => {
                    dropdown.style.display = 'none';
                });
            }
        }
    });

    // Fonction pour récupérer et afficher les projets
    function fetchAndDisplayProjects() {
        fetch('http://localhost:3000/api/projects')
            .then(response => response.json())
            .then(projects => {
                const projectsHTML = projects.map(generateProjectHTML).join('');
                projectsList.innerHTML = projectsHTML;

                // Ajouter des écouteurs d'événements pour les boutons modifier et supprimer
                document.querySelectorAll('.edit-project').forEach(button => {
                    button.addEventListener('click', function (event) {
                        event.preventDefault();
                        const projectId = this.getAttribute('data-id');
                        editProject(projectId);
                    });
                });

                document.querySelectorAll('.delete-project').forEach(button => {
                    button.addEventListener('click', function (event) {
                        event.preventDefault();
                        const projectId = this.getAttribute('data-id');
                        deleteProject(projectId);
                    });
                });
            })
            .catch(error => console.error('Erreur lors de la récupération des projets:', error));
    }

    // Fonction pour récupérer et afficher les notes de cours
    function fetchAndDisplayNotes() {
        fetch('http://localhost:3000/api/notes_de_cours')
            .then(response => response.json())
            .then(notes => {
                const notesHTML = notes.map(generateNoteHTML).join('');
                notesList.innerHTML = notesHTML;

                // Ajouter des écouteurs d'événements pour les boutons modifier et supprimer
                document.querySelectorAll('.edit-note').forEach(button => {
                    button.addEventListener('click', function (event) {
                        event.preventDefault();
                        const noteId = this.getAttribute('data-id');
                        editNote(noteId);
                    });
                });

                document.querySelectorAll('.delete-note').forEach(button => {
                    button.addEventListener('click', function (event) {
                        event.preventDefault();
                        const noteId = this.getAttribute('data-id');
                        deleteNote(noteId);
                    });
                });
            })
            .catch(error => console.error('Erreur lors de la récupération des notes:', error));
    }

    // Fonction pour afficher les sections
    window.showSection = function(section) {
        document.querySelectorAll('.projects-section').forEach(function (el) {
            el.style.display = 'none';
        });
        document.getElementById(section).style.display = 'block';
    }

    // Ajouter les fonctions de modification et de suppression des projets
    function editProject(id) {
        // Logique pour afficher le modal de modification avec les informations du projet
        const project = projectsList.querySelector(`.edit-project[data-id="${id}"]`).closest('.project-box');
        const projectId = id;
        const projectName = project.querySelector('.box-content-header').textContent;
        const projectDescription = project.querySelector('.box-content-subheader').textContent;
        const projectAnneeSession = project.querySelector('.box-content-annee-session').textContent;
        const projectUrlGit = project.querySelector('.box-content-url-git').textContent;
        const projectVideo = project.querySelector('.box-content-video').textContent;

        document.getElementById('edit-project-id').value = projectId;
        document.getElementById('edit-project-name').value = projectName;
        document.getElementById('edit-project-description').value = projectDescription;
        document.getElementById('edit-project-annee-session').value = projectAnneeSession;
        document.getElementById('edit-project-url-git').value = projectUrlGit;
        document.getElementById('edit-project-video').value = projectVideo;

        document.getElementById('edit-project-modal').style.display = 'block';
    }

    function deleteProject(id) {
        fetch(`http://localhost:3000/api/projects/${id}`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => {
                if (data.message === 'Projet supprimé avec succès') {
                    fetchAndDisplayProjects();
                } else {
                    alert('Erreur lors de la suppression du projet.');
                }
            })
            .catch(error => console.error('Erreur lors de la suppression du projet:', error));
    }

    // Ajouter les fonctions de modification et de suppression des notes de cours
    function editNote(id) {
        // Logique pour afficher le modal de modification avec les informations de la note
        const note = notesList.querySelector(`.edit-note[data-id="${id}"]`).closest('.note-box');
        const noteId = id;
        const noteTitle = note.querySelector('.box-content-header').textContent;
        const noteContent = note.querySelector('.box-content-subheader').textContent;

        document.getElementById('edit-note-id').value = noteId;
        document.getElementById('edit-note-title').value = noteTitle;
        document.getElementById('edit-note-content').value = noteContent;

        document.getElementById('edit-note-modal').style.display = 'block';
    }

    function deleteNote(id) {
        fetch(`http://localhost:3000/api/notes_de_cours/${id}`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => {
                if (data.message === 'Note supprimée avec succès') {
                    fetchAndDisplayNotes();
                } else {
                    alert('Erreur lors de la suppression de la note.');
                }
            })
            .catch(error => console.error('Erreur lors de la suppression de la note:', error));
    }

    // Soumettre le formulaire de modification de projet
    document.getElementById('edit-project-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const projectId = document.getElementById('edit-project-id').value;
        const projectName = document.getElementById('edit-project-name').value;
        const projectDescription = document.getElementById('edit-project-description').value;
        const projectAnneeSession = document.getElementById('edit-project-annee-session').value;
        const projectUrlGit = document.getElementById('edit-project-url-git').value;
        const projectVideo = document.getElementById('edit-project-video').value;

        fetch(`http://localhost:3000/api/projects/${projectId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                nom_projet: projectName,
                description: projectDescription,
                annee_session: projectAnneeSession,
                url_projet_git: projectUrlGit,
                video: projectVideo
            })
        })
            .then(response => response.json())
            .then(data => {
                if (data.message === 'Projet mis à jour avec succès') {
                    document.getElementById('edit-project-modal').style.display = 'none';
                    fetchAndDisplayProjects();
                } else {
                    alert('Erreur lors de la mise à jour du projet.');
                }
            })
            .catch(error => console.error('Erreur lors de la mise à jour du projet:', error));
    });

    // Soumettre le formulaire de modification de note
    document.getElementById('edit-note-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const noteId = document.getElementById('edit-note-id').value;
        const noteTitle = document.getElementById('edit-note-title').value;
        const noteContent = document.getElementById('edit-note-content').value;

        fetch(`http://localhost:3000/api/notes_de_cours/${noteId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                nom: noteTitle,
                contenu: noteContent
            })
        })
            .then(response => response.json())
            .then(data => {
                if (data.message === 'Note mise à jour avec succès') {
                    document.getElementById('edit-note-modal').style.display = 'none';
                    fetchAndDisplayNotes();
                } else {
                    alert('Erreur lors de la mise à jour de la note.');
                }
            })
            .catch(error => console.error('Erreur lors de la mise à jour de la note:', error));
    });

    fetchAndDisplayProjects();
    fetchAndDisplayNotes();

    // Fonction pour générer le HTML des utilisateurs
    function generateUserHTML(user) {
        const isDeactivated = user.email.startsWith('deactivated_');
        const userEmail = isDeactivated ? user.email.substring(12) : user.email;
        const userStatus = isDeactivated ? 'Désactivé' : 'Actif';
        const toggleAction = isDeactivated ? 'Réactiver' : 'Désactiver';
        const toggleClass = isDeactivated ? 'reactivate-user' : 'deactivate-user';

        return `
        <div class="user-box-wrapper">
            <div class="user-box" data-id="${user.id}">
                <p class="user-name">${user.nom} ${user.prenom}</p>
                <p class="user-email">${userEmail}</p>
                <p class="user-status">${userStatus}</p>
                <button class="edit-user" data-id="${user.id}">Modifier</button>
                <button class="${toggleClass}" data-id="${user.id}">${toggleAction}</button>
                <button class="delete-user" data-id="${user.id}">Supprimer</button>
            </div>
        </div>
    `;
    }

    // Afficher les utilisateurs (étudiants et professeurs)
    function fetchAndDisplayUsers(userType) {
        fetch(`http://localhost:3000/api/utilisateurs?type=${userType}`)
            .then(response => response.json())
            .then(users => {
                const userHTML = users.map(generateUserHTML).join('');
                if (userType === 'etudiant') {
                    document.getElementById('etudiant-list').innerHTML = userHTML;
                } else {
                    document.getElementById('professeur-list').innerHTML = userHTML;
                }

                // Ajouter des écouteurs d'événements pour les boutons modifier et supprimer
                document.querySelectorAll('.edit-user').forEach(button => {
                    button.addEventListener('click', function (event) {
                        event.preventDefault();
                        const userId = this.getAttribute('data-id');
                        editUser(userId);
                    });
                });

                document.querySelectorAll('.delete-user').forEach(button => {
                    button.addEventListener('click', function (event) {
                        event.preventDefault();
                        const userId = this.getAttribute('data-id');
                        deleteUser(userId);
                    });
                });
            })
            .catch(error => console.error(`Erreur lors de la récupération des ${userType}s:`, error));
    }

    // Fonction pour ajouter ou modifier un utilisateur
    function editUser(id) {
        if (id) {
            fetch(`http://localhost:3000/api/utilisateurs/${id}`)
                .then(response => response.json())
                .then(user => {
                    document.getElementById('edit-user-id').value = user.id;
                    document.getElementById('edit-user-nom').value = user.nom;
                    document.getElementById('edit-user-prenom').value = user.prenom;
                    document.getElementById('edit-user-email').value = user.email;
                    document.getElementById('edit-user-motdepasse').value = user.mot_de_passe;
                    document.getElementById('edit-user-accounttype').value = user.accounttype_id;
                    document.getElementById('edit-user-modal').style.display = 'block';
                });
        } else {
            document.getElementById('edit-user-form').reset();
            document.getElementById('edit-user-id').value = '';
            document.getElementById('edit-user-modal').style.display = 'block';
        }
    }

    function deleteUser(id) {
        fetch(`http://localhost:3000/api/utilisateurs/${id}`, {
            method: 'DELETE'
        })
            .then(response => response.json())
            .then(data => {
                if (data.message === 'Utilisateur supprimé avec succès') {
                    fetchAndDisplayUsers('etudiant');
                    fetchAndDisplayUsers('professeur');
                } else {
                    alert('Erreur lors de la suppression de l\'utilisateur.');
                }
            })
            .catch(error => console.error('Erreur lors de la suppression de l\'utilisateur:', error));
    }

    // Soumettre le formulaire de modification d'utilisateur
    document.getElementById('edit-user-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const userId = document.getElementById('edit-user-id').value;
        const userNom = document.getElementById('edit-user-nom').value;
        const userPrenom = document.getElementById('edit-user-prenom').value;
        const userEmail = document.getElementById('edit-user-email').value;
        const userMotdepasse = document.getElementById('edit-user-motdepasse').value;
        const userAccounttype = document.getElementById('edit-user-accounttype').value;

        const method = userId ? 'PUT' : 'POST';
        const url = userId ? `http://localhost:3000/api/utilisateurs/${userId}` : 'http://localhost:3000/api/utilisateurs';

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                nom: userNom,
                prenom: userPrenom,
                email: userEmail.startsWith('deactivated_') ? userEmail.substring(12) : userEmail,
                mot_de_passe: userMotdepasse,
                accounttype_id: userAccounttype
            })
        })
            .then(response => response.json())
            .then(data => {
                if (data.message === 'Utilisateur mis à jour avec succès' || data.message === 'Utilisateur ajouté avec succès') {
                    document.getElementById('edit-user-modal').style.display = 'none';
                    fetchAndDisplayUsers('etudiant');
                    fetchAndDisplayUsers('professeur');
                } else {
                    alert('Erreur lors de la sauvegarde de l\'utilisateur.');
                }
            })
            .catch(error => console.error('Erreur lors de la sauvegarde de l\'utilisateur:', error));
    });

    document.querySelectorAll('.add-user-btn').forEach(button => {
        button.addEventListener('click', function () {
            const userType = this.getAttribute('data-user-type');
            editUser();
            document.getElementById('edit-user-accounttype').value = userType === 'etudiant' ? 2 : 3;
        });
    });

    function toggleUserStatus(id, action) {
        const url = action === 'desactiver'
            ? `http://localhost:3000/api/utilisateurs/desactiver/${id}`
            : `http://localhost:3000/api/utilisateurs/reactiver/${id}`;

        fetch(url, {
            method: 'PUT'
        })
            .then(response => response.json())
            .then(data => {
                if (data.message.includes('succès')) {
                    fetchAndDisplayUsers('etudiant');
                    fetchAndDisplayUsers('professeur');
                } else {
                    alert(`Erreur lors de la ${action} de l'utilisateur.`);
                }
            })
            .catch(error => console.error(`Erreur lors de la ${action} de l'utilisateur:`, error));
    }

    document.addEventListener('click', function (event) {
        if (event.target.classList.contains('deactivate-user')) {
            const userId = event.target.getAttribute('data-id');
            toggleUserStatus(userId, 'desactiver');
        }

        if (event.target.classList.contains('reactivate-user')) {
            const userId = event.target.getAttribute('data-id');
            toggleUserStatus(userId, 'reactiver');
        }
    });

    function generateMessageHTML(message) {
        return `
        <div class="message-box">
            
            <div class="message-content">
                <div class="message-header">
                    <div class="name">Admin</div>
                    <div class="star-checkbox">
                        <input type="checkbox" id="star-1">
                        <label for="star-1">
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-star">
                                <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2" /></svg>
                        </label>
                    </div>
                </div>
                <p class="message-line">${message.content}</p>
                <p class="message-line time">${message.date}</p>
            </div>
        </div>
    `;
    }

    // Fonction pour récupérer et afficher les messages
    function fetchAndDisplayMessages() {
        const messages = JSON.parse(localStorage.getItem('adminMessages')) || [];
        const messageList = document.getElementById('message-list');
        const messagesHTML = messages.map(generateMessageHTML).join('');
        messageList.innerHTML = messagesHTML;
    }

    // Fonction pour ajouter un message
    function addMessage(messageContent) {
        const messages = JSON.parse(localStorage.getItem('adminMessages')) || [];
        const date = new Date();
        const formattedDate = date.toLocaleDateString() + ' ' + date.toLocaleTimeString();
        const message = { content: messageContent, date: formattedDate };
        messages.push(message);
        localStorage.setItem('adminMessages', JSON.stringify(messages));
        fetchAndDisplayMessages();
    }

    // Écouteur d'événement pour le formulaire d'ajout de message
    document.getElementById('message-form').addEventListener('submit', function (event) {
        event.preventDefault();
        const messageContent = document.getElementById('message-content').value;
        if (messageContent.trim() !== '') {
            addMessage(messageContent.trim());
            document.getElementById('message-content').value = '';
        }
    });

    // Initialiser l'affichage des messages
    fetchAndDisplayMessages();

    fetchAndDisplayUsers('etudiant');
    fetchAndDisplayUsers('professeur');

    // Gérer la déconnexion
    document.querySelector('.profile-btn').addEventListener('click', function (event) {
        const dropdown = this.querySelector('.dropdown-content');
        dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
    });

    document.querySelector('.profile-btn .dropdown-content a').addEventListener('click', function (event) {
        event.preventDefault();
        fetch('/deconnexion', { method: 'GET' })
            .then(response => {
                if (response.ok) {
                    window.location.href = '/';
                } else {
                    alert('Erreur lors de la déconnexion.');
                }
            })
            .catch(error => console.error('Erreur lors de la déconnexion:', error));
    });
});
