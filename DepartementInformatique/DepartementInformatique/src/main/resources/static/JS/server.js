const express = require('express');
const mysql = require('mysql');
const cors = require('cors');

const app = express();
const port = 3000;

// Configurer CORS pour permettre les requêtes du frontend
app.use(cors());

app.use(express.json());

// Configurer la connexion à la base de données
const db = mysql.createConnection({
    host: 'localhost',
    user: 'root', // votre utilisateur MySQL
    password: 'root', // votre mot de passe MySQL
    database: 'departementdb'
});

db.connect(err => {
    if (err) {
        throw err;
    }
    console.log('Connecté à la base de données MySQL');
});

// Route pour obtenir les projets
app.get('/api/projects', (req, res) => {
    const sql = 'SELECT * FROM projets';
    db.query(sql, (err, results) => {
        if (err) throw err;
        res.json(results);
    });
});

// Route pour obtenir les notes de cours
app.get('/api/notes_de_cours', (req, res) => {
    const sql = 'SELECT * FROM notes_de_cours';
    db.query(sql, (err, results) => {
        if (err) throw err;
        res.json(results);
    });
});

// Route pour supprimer un projet
app.delete('/api/projects/:id', (req, res) => {
    const projectId = req.params.id;
    const sql = 'DELETE FROM projets WHERE id_projet = ?';
    db.query(sql, [projectId], (err, result) => {
        if (err) throw err;
        if (result.affectedRows === 0) {
            res.status(404).json({ message: 'Projet non trouvé' });
        } else {
            res.status(200).json({ message: 'Projet supprimé avec succès' });
        }
    });
});

// Route pour mettre à jour un projet
app.put('/api/projects/:id', (req, res) => {
    const projectId = req.params.id;
    const { nom_projet, description, annee_session, url_projet_git, video } = req.body;
    const sql = 'UPDATE projets SET nom_projet = ?, description = ?, annee_session = ?, url_projet_git = ?, video = ? WHERE id_projet = ?';
    db.query(sql, [nom_projet, description, annee_session, url_projet_git, video, projectId], (err, result) => {
        if (err) throw err;
        if (result.affectedRows === 0) {
            res.status(404).json({ message: 'Projet non trouvé' });
        } else {
            res.status(200).json({ message: 'Projet mis à jour avec succès' });
        }
    });
});

// Route pour supprimer une note de cours
app.delete('/api/notes_de_cours/:id', (req, res) => {
    const noteId = req.params.id;
    const sql = 'DELETE FROM notes_de_cours WHERE id_notes_de_cours = ?';
    db.query(sql, [noteId], (err, result) => {
        if (err) throw err;
        if (result.affectedRows === 0) {
            res.status(404).json({ message: 'Note non trouvée' });
        } else {
            res.status(200).json({ message: 'Note supprimée avec succès' });
        }
    });
});

// Route pour mettre à jour une note de cours
app.put('/api/notes_de_cours/:id', (req, res) => {
    const noteId = req.params.id;
    const { nom, contenu } = req.body;
    const sql = 'UPDATE notes_de_cours SET nom = ?, contenu = ? WHERE id_notes_de_cours = ?';
    db.query(sql, [nom, contenu, noteId], (err, result) => {
        if (err) throw err;
        if (result.affectedRows === 0) {
            res.status(404).json({ message: 'Note non trouvée' });
        } else {
            res.status(200).json({ message: 'Note mise à jour avec succès' });
        }
    });
});

// Route pour obtenir les utilisateurs par type
app.get('/api/utilisateurs', (req, res) => {
    const userType = req.query.type;
    let accountType;

    if (userType === 'etudiant') {
        accountType = 2; // accounttype_id pour les étudiants
    } else if (userType === 'professeur') {
        accountType = 3; // accounttype_id pour les professeurs
    } else {
        return res.status(400).json({ message: 'Type d\'utilisateur non valide' });
    }

    const sql = 'SELECT * FROM utilisateurs WHERE accounttype_id = ?';
    db.query(sql, [accountType], (err, results) => {
        if (err) throw err;
        res.json(results);
    });
});

// Route pour obtenir un utilisateur par ID
app.get('/api/utilisateurs/:id', (req, res) => {
    const userId = req.params.id;
    const sql = 'SELECT * FROM utilisateurs WHERE id = ?';
    db.query(sql, [userId], (err, result) => {
        if (err) throw err;
        res.json(result[0]);
    });
});

// Route pour ajouter un utilisateur
app.post('/api/utilisateurs', (req, res) => {
    const { nom, prenom, email, mot_de_passe, accounttype_id } = req.body;
    const sql = 'INSERT INTO utilisateurs (nom, prenom, email, mot_de_passe, accounttype_id) VALUES (?, ?, ?, ?, ?)';
    db.query(sql, [nom, prenom, email, mot_de_passe, accounttype_id], (err, result) => {
        if (err) throw err;
        res.status(201).json({ message: 'Utilisateur ajouté avec succès' });
    });
});

// Route pour mettre à jour un utilisateur
app.put('/api/utilisateurs/:id', (req, res) => {
    const userId = req.params.id;
    const { nom, prenom, email, mot_de_passe, accounttype_id } = req.body;
    const sql = 'UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, accounttype_id = ? WHERE id = ?';
    db.query(sql, [nom, prenom, email, mot_de_passe, accounttype_id, userId], (err, result) => {
        if (err) throw err;
        if (result.affectedRows === 0) {
            res.status(404).json({ message: 'Utilisateur non trouvé' });
        } else {
            res.json({ message: 'Utilisateur mis à jour avec succès' });
        }
    });
});

// Route pour supprimer un utilisateur
app.delete('/api/utilisateurs/:id', (req, res) => {
    const userId = req.params.id;
    const sql = 'DELETE FROM utilisateurs WHERE id = ?';
    db.query(sql, [userId], (err, result) => {
        if (err) throw err;
        if (result.affectedRows === 0) {
            res.status(404).json({ message: 'Utilisateur non trouvé' });
        } else {
            res.json({ message: 'Utilisateur supprimé avec succès' });
        }
    });
});
// Route pour désactiver un utilisateur
app.put('/api/utilisateurs/desactiver/:id', (req, res) => {
    const userId = req.params.id;
    const sql = 'UPDATE utilisateurs SET email = CONCAT("deactivated_", email) WHERE id = ?';
    db.query(sql, [userId], (err, result) => {
        if (err) throw err;
        if (result.affectedRows === 0) {
            res.status(404).json({ message: 'Utilisateur non trouvé' });
        } else {
            res.json({ message: 'Utilisateur désactivé avec succès' });
        }
    });
});

// Route pour réactiver un utilisateur
// Route pour réactiver un utilisateur
app.put('/api/utilisateurs/reactiver/:id', (req, res) => {
    const userId = req.params.id;
    const sql = 'UPDATE utilisateurs SET email = REPLACE(email, "deactivated_", "") WHERE email LIKE "deactivated_%" AND id = ?';
    db.query(sql, [userId], (err, result) => {
        if (err) throw err;
        if (result.affectedRows === 0) {
            res.status(404).json({ message: 'Utilisateur non trouvé ou déjà actif' });
        } else {
            res.json({ message: 'Utilisateur réactivé avec succès' });
        }
    });
});



app.listen(port, () => {
    console.log(`Serveur démarré sur le port ${port}`);
});
