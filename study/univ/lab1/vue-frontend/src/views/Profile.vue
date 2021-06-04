<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{currentUser.username}}</strong> Profile
      </h3>
    </header>
    <p>
      <strong>Token:</strong>
      {{currentUser.jwt.substring(0, 20)}} ... {{currentUser.jwt.substr(currentUser.jwt.length - 20)}}
    </p>
    <p>
      <strong>Id:</strong>
      {{currentUser.id}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.email}}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul>
    <strong>Posts:</strong>
    <ul>
      <li v-for="post in currentUser.posts">{{post.postData}}</li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';
import authHeader from "../services/auth-header";

export default {

  name: 'Profile',
  computed: {
    currentUser() {
      return localStorage.getItem('user');
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>