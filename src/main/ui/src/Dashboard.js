import * as React from 'react';
import { styled, createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import Link from '@mui/material/Link';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import Navigation from './Navigation';
import EntropyStatusValue from './EntropyStatusValue';
import FileChooser from './FileChooser';
import Credentials from './Credentials';
import About from './About';
import axios from 'axios';

import Settings from './settings.json';

const getWhoamiUrl = Settings.serviceUrl + Settings.whoamiEndpoint;
const getEntropyStatusUrl = Settings.serviceUrl + Settings.getEntropyStatusEndpoint;
const uploadEntropyUrl = Settings.serviceUrl + Settings.uploadEntropyEndpoint;
const checkCredentialsUrl = Settings.serviceUrl + Settings.checkCredentialsEndpoint;

function Copyright(props) {
  return (
    <Typography variant="body2" color="text.secondary" align="center" {...props}>
      {'Copyright © '}
      <Link color="inherit" href="https://mui.com/">
        Your Website
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const drawerWidth = 240;

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    '& .MuiDrawer-paper': {
      position: 'relative',
      whiteSpace: 'nowrap',
      width: drawerWidth,
      transition: theme.transitions.create('width', {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.enteringScreen,
      }),
      boxSizing: 'border-box',
      ...(!open && {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
          easing: theme.transitions.easing.sharp,
          duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing(7),
        [theme.breakpoints.up('sm')]: {
          width: theme.spacing(9),
        },
      }),
    },
  }),
);

const mdTheme = createTheme();

// Handles responses from either the Java server or the Node server
function adaptDataAsRequired( entropyStatus ) {
  let adaptedEntropyStatus = entropyStatus;

  if ( !entropyStatus.entropySize ) {
    adaptedEntropyStatus.entropySize = entropyStatus.bytecount;
    adaptedEntropyStatus.entropyStatus = entropyStatus.status;
  }

  return adaptedEntropyStatus;
}

function DashboardContent(props) {
  const [open, setOpen] = React.useState(true);
  const [aboutIsOpen, setAboutIsOpen] = React.useState(false);
  const [entropyFile, setEntropyFile] = React.useState({name:''});
  const [entropyStatus, setEntropyStatus] = React.useState({ entropySize : 0, entropyStatus : 'NORMAL'});
  const [whoami, setWhoami] = React.useState("");
  const [smCreds, setSmCreds] = React.useState({ usertype : 'SM', username: 'SM User', password: '', valid : false});
  const [kgmCreds, setKgmCreds] = React.useState({ usertype : 'KGM', username: 'KGM User', password: '', valid : false});
  
  const toggleDrawer = () => {
    setOpen(!open);
  };

  const getEntropyStatus = () => {
    fetch( getEntropyStatusUrl, {mode : "cors"} )
    .then( (response) => {
        return response.json();
    })
    .then( (data) => {
      console.log( "New Entropy Status = " );
      console.log( data );
      setEntropyStatus( adaptDataAsRequired(data) );
    });
  }

  const getWhoami = () => {
    fetch( getWhoamiUrl, {mode : "cors"} )
    .then( (response) => {
        return response.json();
    })
    .then( (data) => {
      console.log( "Whoami = " );
      console.log( data );
      setWhoami( data.message );
    });
  }

  React.useEffect(()=>{
    getWhoami();
  },[]);

  // This is creates a declarative version of setInterval that plays well with React 
  // (https://overreacted.io/making-setinterval-declarative-with-react-hooks/)
  function useInterval(callback, delay) {
    const savedCallback = React.useRef();
  
    React.useEffect(() => {
      savedCallback.current = callback;
    });
  
    React.useEffect(() => {
      function tick() {
        savedCallback.current();
      }
  
      let id = setInterval(tick, delay);
      return () => clearInterval(id);
    }, [delay]);
  }

  // Refresh entropy status
  useInterval(() => {
      getEntropyStatus();
  }, 3000);

  const showAbout = () => {
    setAboutIsOpen(true);
  }

  const closeAbout = () => {
    setAboutIsOpen(false);
  }

  const checkCredentials = (usertype,newUsername,newPassword) => {
    if ( usertype === 'SM' ) {
      const newCreds = { ...smCreds, username : newUsername , password : newPassword };
      setSmCreds(newCreds);
      uploadCredentials(newCreds)
    } else {
      const newCreds = { ...kgmCreds, username : newUsername , password : newPassword };
      setKgmCreds( newCreds );
      uploadCredentials(newCreds);
    }
    console.log("Checking credentials for " + usertype + " user updated : username = " +  newUsername + " password = " + newPassword);
  }

  const chooseEntropyFile = (file) => {
    setEntropyFile(file);
  }

  const uploadCredentials = (credentials) => {
    // Details of the creds to be uploaded
    console.log("uploading credentials");
    console.log(credentials);
  
    console.log( checkCredentialsUrl);

    const credentialsDTO = { userType: credentials.usertype,
                             username: credentials.username,
                             password: credentials.password };

    // Request made to the backend api
    // Send credentials DTO object
    axios.post(checkCredentialsUrl, credentialsDTO)
    .then( (response) => {
      let isValid = false;
      console.log( response );
      if ( response.data.userOK ) {
        isValid = true;
      }

      if ( credentials.usertype === 'SM' ) {
        setSmCreds( { ...smCreds, valid : isValid });
      } else {
        setKgmCreds( { ...kgmCreds, valid : isValid });
      }
    });
  }

  const checkDataValid = () => {
    let valid = true;
    if ( entropyFile.name === '' ) {
      alert( 'Entropy file has not been selected');
      valid = false;
    }

    if ( !smCreds.valid ) {
      alert( 'SM User creds have not been successfully validated');
      valid = false;
    }

    if ( !kgmCreds.valid ) {
      alert( 'KGM User creds have not been successfully validated');
      valid = false;
    }

    return valid;
  }

  const uploadEntropy = () => {
    if ( checkDataValid() ) {

      // Details of the uploaded file
      console.log(entropyFile);
    
      // Create an object of formData
      const formData = new FormData();
      
      // Update the formData object
      formData.append(
        "data",
        entropyFile,
        entropyFile.name
      );
    
      // Request made to the backend api
      // Send formData object
      axios.post(uploadEntropyUrl, formData);
    }
}

  return (
    <ThemeProvider theme={mdTheme}>
      <About showAbout={aboutIsOpen} closeAbout={closeAbout}/>
      <Box sx={{ display: 'flex' }}>
        <CssBaseline />
        <AppBar position="absolute" open={open}>
          <Toolbar
            sx={{
              pr: '24px', // keep right padding when drawer closed
            }}
          >
            <IconButton
              edge="start"
              color="inherit"
              aria-label="open drawer"
              onClick={toggleDrawer}
              sx={{
                marginRight: '36px',
                ...(open && { display: 'none' }),
              }}
            >
              <MenuIcon />
            </IconButton>
            <Typography
              component="h1"
              variant="h6"
              color="inherit"
              noWrap
              sx={{ flexGrow: 1 }}
            >
              Entropy Manager Dashboard (Backend server is {whoami})
            </Typography>
          </Toolbar>
        </AppBar>
        <Drawer variant="permanent" open={open}>
          <Toolbar
            sx={{
              display: 'flex',
              alignItems: 'center',
              justifyContent: 'flex-end',
              px: [1],
            }}
          >
            <IconButton onClick={toggleDrawer}>
              <ChevronLeftIcon />
            </IconButton>
          </Toolbar>
          <Divider />
          <Navigation uploadEntropy={uploadEntropy} showAbout={showAbout} />
        </Drawer>
        <Box
          component="main"
          sx={{
            backgroundColor: (theme) =>
              theme.palette.mode === 'light'
                ? theme.palette.grey[100]
                : theme.palette.grey[900],
            flexGrow: 1,
            height: '100vh',
            overflow: 'auto',
          }}
        >
          <Toolbar />
          <Container maxWidth="lg" sx={{ mt: 4, mb: 4 }}>
            <Grid container spacing={3}>
              {/* Entropy Status Value */}
              <Grid item xs={12}>
                <Paper
                  sx={{
                    p: 2,
                    display: 'flex',
                    flexDirection: 'column',
                    height: 140,
                  }}
                >
                  <EntropyStatusValue size={entropyStatus.entropySize} status={entropyStatus.entropyStatus}/>
                </Paper>
              </Grid>
              <Grid item xs={12}>
                <Paper
                  sx={{
                    p: 2,
                    display: 'flex',
                    flexDirection: 'column',
                    height: 140,
                  }}
                >
                  <FileChooser file={entropyFile} fileSelected={chooseEntropyFile}/>
                </Paper>
              </Grid>
              <Grid item xs={12}>
                <Paper
                  sx={{
                    p: 2,
                    display: 'flex',
                    flexDirection: 'column',
                    height: 280,
                  }} 
                > 
                  <Credentials checkCredentials={checkCredentials} smCreds={smCreds} kgmCreds={kgmCreds}/>
                </Paper>
              </Grid>
              <Grid item xs={12}>
                <Copyright sx={{ pt: 4 }} />
                </Grid>
              </Grid>  
          </Container>
        </Box>
      </Box>
    </ThemeProvider>
  );
}

export default function Dashboard(props) {
  return <DashboardContent {...props}/>;
}
