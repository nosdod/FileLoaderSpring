import * as React from 'react';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import DashboardIcon from '@mui/icons-material/Dashboard';
import UploadFileIcon from '@mui/icons-material/UploadFile';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import InfoIcon from '@mui/icons-material/Info';

export default function Navigation({uploadEntropy,showAbout}) {
    return(
        <React.Fragment>
        <ListItem button>
            <ListItemIcon>
            <DashboardIcon />
            </ListItemIcon>
            <ListItemText primary="Dashboard" />
        </ListItem>
        <ListItem button onClick={uploadEntropy}>
            <ListItemIcon>
            <UploadFileIcon />
            </ListItemIcon>
            <ListItemText primary="Load Entropy" />
        </ListItem>
        <ListItem button onClick={showAbout}>
            <ListItemIcon>
            <InfoIcon />
            </ListItemIcon>
            <ListItemText primary="About" />
        </ListItem>
        <ListItem button>
            <ListItemIcon>
            <ExitToAppIcon />
            </ListItemIcon>
            <ListItemText primary="Quit" />
        </ListItem>
        </React.Fragment>
    );
  }