import { Component, OnInit }
import {  }
import {  }

@Compponent({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.ts']
})
export class PanierComponent implements OnInit {

  constructor(private http: HttpClient, private route: ActivatedRoute) { }
  
  ngOnInit() {
    this.route.params.subscribe(params: Params) => {
      console.log(params);
      this.http.post('http://localhost:888/panier/ajout')
    }
  }
}