// import { Injectable } from '@angular/core';
// import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { map } from 'rxjs/operators';

// @Injectable({ providedIn: 'root' })
// export class ProduitsService {

// 	private urlBase: string = 'http://localhost:8888/';

// 	constructor(private http: HttpClient) { }

// 	getProduits(): Observable<any> {
		
// 		console.log("Dans le service ProduitsService avec " + this.urlBase + 'produits');
	
// 		return this.http.get(this.urlBase + 'produits');
// 	}

// 	getProduitsParCategorie(categorie): Observable<any> {

// 		console.log("Dans le service ProduitsService avec " + this.urlBase + 'produits/' + categorie);

// 		return this.http.get(this.urlBase + 'produits/' + categorie);
// 	}

// 	getCategories(): Observable<any> {

// 		console.log("Dans le service ProduitsService avec " + this.urlBase + 'categories');

// 		return this.http.get(this.urlBase + 'categories');
// 	} 
// }
