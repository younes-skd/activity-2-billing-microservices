import {Component} from '@angular/core';
    import {bootstrapApplication} from '@angular/platform-browser';
    import {provideHttpClient} from '@angular/common/http';
    import {provideRouter,RouterOutlet,RouterLink,Routes} from '@angular/router';
    import {Products} from './app/products';import {Home} from './app/home';import {Customers,Bills} from './app/billing';
    const routes:Routes=[{path:'home',component:Home},{path:'products',component:Products},{path:'customers',component:Customers},{path:'bills',component:Bills},{path:'',redirectTo:'home',pathMatch:'full'},{path:'**',redirectTo:'home'}];
    @Component({selector:'app-root',standalone:true,imports:[RouterOutlet,RouterLink],template:`<nav class="navbar navbar-expand mb-4"><div class="container"><a class="navbar-brand fw-bold" routerLink="/home">EMSI / LAB</a><div class="navbar-nav"><a class="nav-link" routerLink="/home">Accueil</a><a class="nav-link" routerLink="/products">Produits</a><a class="nav-link" routerLink="/customers">Clients</a><a class="nav-link" routerLink="/bills">Factures</a></div></div></nav><main class="container pb-5"><router-outlet/></main>`})
    class App {}
    bootstrapApplication(App,{providers:[provideHttpClient(),provideRouter(routes)]}).catch(console.error);
