
document.addEventListener('DOMContentLoaded', () => {
  "use strict";

  /*
  * haeder 
  */ 
  const header = document.querySelector("header");

  let lastScrollY = 0;

  function handleScroll() {
      const currentScrollY = window.scrollY;
      if (Math.abs(currentScrollY - lastScrollY) > 3) {
          if (currentScrollY > 3) {
              header.classList.add("act");
          } else {
              header.classList.remove("act");
          }
          lastScrollY = currentScrollY;
      }
  }

  document.addEventListener('scroll', handleScroll);

  /**
   * Preloader
   */
  const preloader = document.querySelector('#preloader');
  if (preloader) {
    window.addEventListener('load', () => {
      setTimeout(() => {
        preloader.classList.add('loaded');
      }, 400);
      setTimeout(() => {
        preloader.remove();
      }, 1200);
    });
  }

  /**
   * Mobile nav toggle
   */
  const mobileNavShow = document.querySelector('.mobile-nav-show');
  const mobileNavHide = document.querySelector('.mobile-nav-hide');

  document.querySelectorAll('.mobile-nav-toggle').forEach(el => {
    el.addEventListener('click', function(event) {
      event.preventDefault();
      mobileNavToogle();
    })
  });

  function mobileNavToogle() {
    document.querySelector('body').classList.toggle('mobile-nav-active');
    mobileNavShow.classList.toggle('d-none');
    mobileNavHide.classList.toggle('d-none');
  }

  /**
   * Hide mobile nav on same-page/hash links
   */
  document.querySelectorAll('#navbar a').forEach(navbarlink => {

    if (!navbarlink.hash) return;

    let section = document.querySelector(navbarlink.hash);
    if (!section) return;

    navbarlink.addEventListener('click', () => {
      if (document.querySelector('.mobile-nav-active')) {
        mobileNavToogle();
      }
    });

  });

  /**
   * Toggle mobile nav dropdowns
   */
  const navDropdowns = document.querySelectorAll('.navbar .dropdown > a');

  navDropdowns.forEach(el => {
    el.addEventListener('click', function(event) {
      if (document.querySelector('.mobile-nav-active')) {
        event.preventDefault();
        this.classList.toggle('active');
        this.nextElementSibling.classList.toggle('dropdown-active');

        // let dropDownIndicator = this.querySelector('.dropdown-indicator');
        // dropDownIndicator.classList.toggle('bi-chevron-up');
        // dropDownIndicator.classList.toggle('bi-chevron-down');
      }
    })
  });

  /**
   * Scroll top button
   */
  const scrollTop = document.querySelector('.scroll-top');
  if (scrollTop) {
    const togglescrollTop = function() {
      window.scrollY > 100 ? scrollTop.classList.add('active') : scrollTop.classList.remove('active');
    }
    window.addEventListener('load', togglescrollTop);
    document.addEventListener('scroll', togglescrollTop);
    scrollTop.addEventListener('click', window.scrollTo({
      top: 0,
      behavior: 'smooth'
    }));
  }

  /**
   * Initiate glightbox
   */
  const glightbox = GLightbox({
    selector: '.glightbox',
    touchNavigation: true,
    loop: true,
    autoplayVideos: true,
    width: "90vw",
    height: "90vh"
  });

  /**
   * Init swiper slider with 1 slide at once in desktop view
   */
  new Swiper('.slides-1', {
    speed: 600,
    loop: true,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false
    },
    slidesPerView: 'auto',
    pagination: {
      el: '.swiper-pagination',
      type: 'bullets',
      clickable: true
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    }
  });

  /**
   * Init swiper slider with 3 slides at once in desktop view
   */
  new Swiper('.slides-3', {
    speed: 600,
    loop: true,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false
    },
    slidesPerView: 'auto',
    pagination: {
      el: '.swiper-pagination',
      type: 'bullets',
      clickable: true
    },
    navigation: {
      nextEl: '.swiper-button-next',
      prevEl: '.swiper-button-prev',
    },
    breakpoints: {
      320: {
        slidesPerView: 1,
        spaceBetween: 40
      },

      1200: {
        slidesPerView: 3,
      }
    }
  });

  
  new Swiper('.slides-icon', {
    speed: 300,
    loop: true,
    autoplay: {
      delay: 2000,
      disableOnInteraction: false
    },
    slidesPerView: 'auto',
    breakpoints: {
      280: {
        slidesPerView: 2
      },
      400: {
        slidesPerView: 3
      },
      600: {
        slidesPerView: 4
      },
      800: {
        slidesPerView: 6
      },
      1000: {
        slidesPerView: 8
      },
      1300: {
        slidesPerView: 10
      }
    }
  });

  new Swiper('.slides-story', {
    speed: 600,
    loop: true,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false
    },
    slidesPerView: 'auto',
    spaceBetween: 15,
    pagination: {
      el: ".swiper-pagination",
      type: "fraction"
    },
    breakpoints: {
      600: {
        slidesPerView: 2
      },
      900: {
        slidesPerView: 3
      },
      1100: {
        slidesPerView: 4
      },
      1400: {
        slidesPerView: 5
      },
      1600: {
        slidesPerView: 6
      }
    }
  });

  /**
   * Animation on scroll function and init
   */
  function aos_init() {
    AOS.init({
      duration: 1000,
      easing: 'ease-in-out',
      once: true,
      mirror: false
    });
  }
  window.addEventListener('load', () => {
    aos_init();
  });


  (() => {
    'use strict'
  
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    const forms = document.querySelectorAll('.needs-validation')
  
    // Loop over them and prevent submission
    Array.from(forms).forEach(form => {
      form.addEventListener('submit', event => {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }
  
        form.classList.add('was-validated')
      }, false)
    })
  })();


$(document).ready(function() {
    var obj = $(".file-drop");
    var label = obj.find('label');
    var uploadInput = $('#upload-file');

    obj.on('dragenter', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).css('border', '2px solid var(--color-orange-900)');
    });

    obj.on('dragover', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).css('border', '2px solid var(--color-orange-900)');
    });

    obj.on('drop', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).css('border', '2px dotted var(--color-orange-900)');
        var files = e.originalEvent.dataTransfer.files;
        handleFiles(files);
    });

    uploadInput.on('change', function(e) {
        var files = e.target.files;
        handleFiles(files);
    });

    function handleFiles(files) {
        var fileNames = [];
        for (var i = 0; i < files.length; i++) {
            fileNames.push(files[i].name);
            label.css('color', 'black'); 
        }
        label.text(fileNames.join(', ')); // 파일 이름들을 라벨에 표시
        var dataTransfer = new DataTransfer();
        for (var i = 0; i < files.length; i++) {
            dataTransfer.items.add(files[i]);
        }
        uploadInput[0].files = dataTransfer.files;
    }
});


/*$(document).ready(function() {
    var obj = $(".file-drop");
    var label = obj.find('label');
    var statusBarContainer = $('<div id="statusBarContainer"></div>');

    obj.on('dragenter', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).css('border', '2px solid var(--color-orange-900)');
    });

    obj.on('dragover', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).css('border', '2px solid var(--color-orange-900)');
    });

    obj.on('drop', function(e) {
        e.stopPropagation();
        e.preventDefault();
        $(this).css('border', '2px dotted var(--color-orange-900)');
        var files = e.originalEvent.dataTransfer.files;
        handleFileUpload(files);
    });

    $('#upload-file').on('change', function(e) {
        e.preventDefault();
        var files = e.target.files;
        handleFileUpload(files);
    });

    function handleFileUpload(files) {
        label.hide(); // Hide the label
        if (!$('#statusBarContainer').length) {
            obj.append(statusBarContainer); // Add status bar container if not already added
        }
        for (var i = 0; i < files.length; i++) {
            var fd = new FormData();
            fd.append('upload', files[i]);

            var status = new createStatusbar($('#statusBarContainer'));
            status.setFileNameSize(files[i].name, files[i].size);

            // AJAX call to upload the file
            $.ajax({
                url: obj.attr('action'),
                type: 'POST',
                data: fd,
                contentType: false,
                processData: false,
                success: function(response) {
                    // handle success response
                    console.log('File uploaded successfully');
                    // Optionally reload or update the file list
                },
                error: function(response) {
                    // handle error response
                    console.log('File upload failed');
                }
            });
        }
    }

    function createStatusbar(obj) {
        this.statusbar = $("<div class='statusbar'></div>");
        this.filename = $("<span class='filename'></span>").appendTo(this.statusbar);
        this.size = $("<span class='filesize'></span>").appendTo(this.statusbar);

        obj.append(this.statusbar);

        this.setFileNameSize = function(name, size) {
            var sizeStr = "";
            var sizeKB = size / 1024;
            if (parseInt(sizeKB) > 1024) {
                var sizeMB = sizeKB / 1024;
                sizeStr = sizeMB.toFixed(2) + " MB";
            } else {
                sizeStr = sizeKB.toFixed(2) + " KB";
            }
            this.filename.html(name);
            this.size.html(sizeStr);
        }
    }
});*/
});