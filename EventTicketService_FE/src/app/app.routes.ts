import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SimulationComponent } from './simulation/simulation.component';
import { UserConfigurationsComponent } from './user-configurations/user-configurations.component';
import {LogComponent} from "./logs/logs.component";


/**
 * Defines the routes for different components in the application.
 */
export const routes: Routes = [
   { path: '',component: HomeComponent},
   {path: 'simulation', component: SimulationComponent},
   {path: 'user-configurations', component: UserConfigurationsComponent},
  {path:'server-logs', component: LogComponent}

];
